package game;

import game.card.Card;
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

	public GameState(Integer numberOfPlayers, Integer onTurn, Set<SleepingQueenPosition> sleepingQueens, Map<HandPosition, Card> cards, Map<AwokenQueenPosition, Queen> awokeenQueens, List<Card> cardsDiscardedLastTurn) {
		this.numberOfPlayers = numberOfPlayers;
		this.onTurn = onTurn;
		this.sleepingQueens = sleepingQueens;
		this.cards = cards;
		this.awokeenQueens = awokeenQueens;
		this.cardsDiscardedLastTurn = cardsDiscardedLastTurn;
	}

	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public Integer getOnTurn() {
		return onTurn;
	}

	public Set<SleepingQueenPosition> getSleepingQueens() {
		return sleepingQueens;
	}

	public Map<HandPosition, Card> getCards() {
		return cards;
	}

	public Map<AwokenQueenPosition, Queen> getAwokeenQueens() {
		return awokeenQueens;
	}

	public List<Card> getCardsDiscardedLastTurn() {
		return cardsDiscardedLastTurn;
	}
}
