package game;

import game.player.Player;
import game.position.Position;
import game.queens.SleepingQueens;

import java.util.List;

public class Game {
	private DrawingAndTrashPile pile;
	private List<Player> players;
	private SleepingQueens sleepingQueens;
	private GameFinishedStrategy gameFinishedStrategy;
	private Integer turn;

	public Game(DrawingAndTrashPile pile, List<Player> players, SleepingQueens sleepingQueens) {
		this.pile = pile;
		this.players = players;
		this.sleepingQueens = sleepingQueens;
		this.turn = 0;
	}

	public GameState play(Integer player, List<Position> cards) {
		// player integer == turn
		if(player == turn) {
			players.get(turn).play(cards);
			++turn;
		}
		// TODO construct game state
		return null;
	}
}
