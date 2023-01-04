package game.drawing;

import game.card.Card;

import java.util.*;

public class DrawingAndTrashPile {
	List<Card> drawingPile;
	List<Card> trashingPile = new ArrayList<>();
	List<Card> cardsDiscardedThisTurn = new ArrayList<>();

	DrawStrategy drawStrategy;

	public DrawingAndTrashPile(List<Card> drawingPile, DrawStrategy drawStrategy) {
		this.drawingPile = drawingPile;
		this.drawStrategy = drawStrategy;
	}

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
