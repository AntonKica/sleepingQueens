package game.drawing;

import game.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawStrategyFull implements DrawStrategy {
	@Override
	public List<Card> draw(List<Card> drawingPile, List<Card> trashingPile, int count) {
		if(drawingPile.size() < count) {
			Collections.shuffle(trashingPile);
			drawingPile.addAll(trashingPile);
			trashingPile.clear();

			if(drawingPile.size() < count)
				throw new RuntimeException("welp");
		}

		var drawCardsTemp = drawingPile.subList(0, count);
		var drawCards = new ArrayList<>(drawCardsTemp);
		drawCardsTemp.clear();

		return drawCards;
	}
}
