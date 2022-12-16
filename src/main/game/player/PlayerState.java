package game.player;

import game.card.Card;
import game.queens.Queen;

import java.util.Map;
import java.util.Optional;

public class PlayerState {
	private Map<Integer, Card> cards;
	private Map<Integer, Queen> awokenQueens;

	public PlayerState(Map<Integer, Card> cards, Map<Integer, Queen> awokenQueens) {
		this.cards = cards;
		this.awokenQueens = awokenQueens;
	}

	public Map<Integer, Card> getCards() {
		return cards;
	}

	public Map<Integer, Queen> getAwokenQueens() {
		return awokenQueens;
	}
}
