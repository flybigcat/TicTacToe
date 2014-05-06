package ttt.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttt.model.Game;
import ttt.model.User;
import ttt.model.dao.GameDao;

@Repository
public class GameDaoImpl implements GameDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Game getGame( Integer id )
	{
		return entityManager.find( Game.class, id );
	}

	
	// Retrieve all the completed games played by a user against the AI players
	@Override
	public List<Game> getGamesAgainstAI( User user )
	{
		return entityManager
				.createQuery(
						"from Game where player1.id = :player1 and player2.id = 2 and outcome is not null",
						Game.class ).setParameter( "player1", user.getId() )
				.getResultList();
	}

	// Retrieve all the saved games by a user
	@Override
	public List<Game> getSavedGames( User user )
	{
		return entityManager
				.createQuery(
						"from Game where outcome is null and player1.id = :player or outcome is null and player2.id = :player",
						Game.class ).setParameter( "player", user.getId() )
				.getResultList();
	}
	
    @Transactional
	@Override
	public Game setGame(Game game){
		return game = entityManager.merge(game);
	}

}