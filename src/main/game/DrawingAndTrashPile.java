package game;

import game.card.Card;

import java.util.*;

public class DrawingAndTrashPile {
	List<Card> drawingPile = new ArrayList<>();
	List<Card> trashingPile = new ArrayList<>();
	List<Card> cardsDiscardedThisTurn = new ArrayList<>();
	public void trash(List<Card> cards) {
		trashingPile.addAll(cards);
		cardsDiscardedThisTurn.addAll(cards);
	}

	public List<Card> draw(int count) {
		if(drawingPile.size() < count && mergeTrashingPile() < count)
			throw new RuntimeException("Welp");

		var list = drawingPile.subList(drawingPile.size() - count, drawingPile.size());
		drawingPile.removeAll(list);

		return list;
	}

	private int mergeTrashingPile() {
		Collections.shuffle(trashingPile);
		drawingPile.addAll(0, trashingPile);
		trashingPile.clear();

		return drawingPile.size();
	}

	public void newTurn() {
		cardsDiscardedThisTurn.clear();
	}

	public List<Card> getCardsDiscardedThisTurn() {
		return cardsDiscardedThisTurn;
	}
}
