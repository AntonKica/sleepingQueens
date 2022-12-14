package game.player;

import game.card.Card;
import game.queens.AwokenQueens;
import game.queens.Queen;

import java.util.Map;
import java.util.Optional;

public class PlayerState {
	private Map<Integer, Optional<Card>> cards;
	private Map<Integer, Queen> awokenQueens;
}
