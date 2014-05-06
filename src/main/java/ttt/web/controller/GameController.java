package ttt.web.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ttt.model.Game;
import ttt.model.User;
import ttt.model.dao.GameDao;
import ttt.model.dao.UserDao;

@Controller
public class GameController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private GameDao gameDao;

	/***************************** player & proceed game ***********************************/
	@RequestMapping(value = "/game/TicTacToeGame.html")
	public String game( ModelMap models,
			@RequestParam(value = "newGame", required = false) Integer newGame,
			@RequestParam(value = "i", required = false) Integer i,
			HttpSession session )
	{

		if ( session.getAttribute( "player" ) == null )
		{
			return "redirect:/user/login.html";
		}

		User player = (User) session.getAttribute( "player" );
		User player2 = userDao.getUser( 2 );

		Game tttGame = null;

		// first time visit
		if ( session.getAttribute( "tttGame" ) == null )
		{
			tttGame = new Game();

			tttGame.setStartDate( new Date() );
			tttGame.setPlayer1( player );
			tttGame.setPlayer2( player2 );

			tttGame = gameDao.setGame( tttGame );
			session.setAttribute( "tttGame", tttGame );

			return "/game/TicTacToeGame";
		}

		// not first time
		if ( session.getAttribute( "tttGame" ) != null )
		{
			tttGame = (Game) session.getAttribute( "tttGame" );
		}

		// restart game without finish
		if ( newGame != null
				&& newGame == 1
				&& ( tttGame.getOutcome() == null || tttGame.getOutcome()
						.equals( "" ) ) )
		{
			tttGame.setEndDate( new Date() );
			tttGame.setOutcome( "loss" );
			tttGame = gameDao.setGame( tttGame );

			tttGame = new Game();
			tttGame.setStartDate( new Date() );
			tttGame.setPlayer1( player );
			tttGame.setPlayer2( player2 );
			tttGame = gameDao.setGame( tttGame );
			session.setAttribute( "tttGame", tttGame );

			return "/game/TicTacToeGame";
		}

		// restart game after finish
		if ( newGame != null && newGame == 1 )
		{
			tttGame = new Game();

			tttGame.setStartDate( new Date() );
			tttGame.setPlayer1( player );
			tttGame.setPlayer2( player2 );

			tttGame = gameDao.setGame( tttGame );
			session.setAttribute( "tttGame", tttGame );

			return "/game/TicTacToeGame";
		}

		if ( i != null )
		{
			tttGame.getBoard().set( i, 1 );

			tttGame = gameDao.setGame( tttGame );

			// check if player 1 win
			if ( tttGame.getOutcome().equals( "win" ) )
			{
				System.out.println( "win" );

				tttGame.setEndDate( new Date() );
				tttGame.setOutcome( "win" );
				models.put( "end", 1 );

				tttGame = gameDao.setGame( tttGame );
				session.setAttribute( "tttGame", tttGame );

				return "/game/TicTacToeGame";
			}

			// check if tied
			if ( tttGame.getOutcome().equals( "tie" ) )
			{
				System.out.println( "tie" );

				tttGame.setEndDate( new Date() );
				tttGame.setOutcome( "tie" );
				models.put( "end", 0 );

				tttGame = gameDao.setGame( tttGame );
				session.setAttribute( "tttGame", tttGame );

				return "/game/TicTacToeGame";
			}

			// check if player 2 can make win step
			int p2WS = tttGame.getP2WinStep();
			// it has win step
			if ( p2WS != -1 )
			{
				tttGame.getBoard().set( p2WS, 2 );

				tttGame.setEndDate( new Date() );
				tttGame.setOutcome( "loss" );
				models.put( "end", 2 );

				tttGame = gameDao.setGame( tttGame );
				session.setAttribute( "tttGame", tttGame );

				System.out.println( "loss" );
				return "/game/TicTacToeGame";
			}

			// check if player 1 can make win step
			int p1WS = tttGame.getP1WinStep();
			// it has win step
			if ( p1WS != -1 )
			{
				tttGame.getBoard().set( p1WS, 2 );
				tttGame = gameDao.setGame( tttGame );
				session.setAttribute( "tttGame", tttGame );

				System.out.println( "avoid" );

				return "/game/TicTacToeGame";
			}

			// random move
			tttGame.getBoard().set( tttGame.getRandomStep(), 2 );
			System.out.println( "random" );
			tttGame = gameDao.setGame( tttGame );
			session.setAttribute( "tttGame", tttGame );
		}
		return "/game/TicTacToeGame";
	}
}
