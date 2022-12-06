package game;

import game.position.Position;
import game.queens.SleepingQueens;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private GameState state;
	private DrawingAndTrashPile pile;
	private ArrayList<Player> players;
	private SleepingQueens sleepingQueens;

	public Game(DrawingAndTrashPile pile, ArrayList<Player> players, SleepingQueens sleepingQueens) {
		this.state = new GameState();
		this.pile = pile;
		this.players = players;
		this.sleepingQueens = sleepingQueens;
	}

	public void play(Integer playerIndex, List<Position> cards) {

	}
}
