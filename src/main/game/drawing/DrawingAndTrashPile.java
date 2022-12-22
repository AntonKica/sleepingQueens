package game.drawing;

import game.card.Card;

import java.util.*;

public class DrawingAndTrashPile {
	List<Card> drawingPile = new ArrayList<>();
	List<Card> trashingPile = new ArrayList<>();
	List<Card> cardsDiscardedThisTurn = new ArrayList<>();

	DrawStrategy drawStrategy;
	public void trash(List<Card> cards) {
		trashingPile.addAll(cards);
		cardsDiscardedThisTurn.addAll(cards);
	}

	public List<Card> draw(int count) {
		return drawStrategy.draw(drawingPile, trashingPile, count);
	}

	public void newTurn() {
		cardsDiscardedThisTurn.clear();
	}

	public List<Card> getCardsDiscardedThisTurn() {
		return cardsDiscardedThisTurn;
	}
}
