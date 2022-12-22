package game.drawing;

import game.card.Card;

import java.util.List;

public interface DrawStrategy {

	List<Card> draw(List<Card> drawingPile, List<Card> trashingPile, int count);
}