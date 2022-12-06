package game;

import game.position.AwokenQueenPosition;
import game.position.HandPosition;
import game.position.SleepingQueenPosition;
import game.queens.Queen;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameState {
	private Integer numberOfPlayers;
	private Integer onTurn;
	private Set<SleepingQueenPosition> sleepingQueens;
	private Map<HandPosition, Card> cards;
	private Map<AwokenQueenPosition, Queen> awokeenQueens;
	private List<Card> cardsDiscardedLastTurn;
}
