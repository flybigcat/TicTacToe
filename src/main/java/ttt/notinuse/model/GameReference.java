package ttt.notinuse.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ttt.model.User;

@Entity
@Table(name = "games")
public class GameReference implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private User player1;

	@ManyToOne
	private User player2;

	private Date startDate;

	// if null, it is not finished, saved game by user, not
	private Date endDate;

	// if null, it is finished
	private Date saveDate;

	// if play1 win, it is "win", player2 win, "loss", or "tie"
	// or just empty"" for incomplete
	private Outcome outcome;
	
	enum Outcome{ONGOING, WIN, LOSS, TIE};
	
	@Transient
	private int board[][];
	
	private String boardString;
	

}
