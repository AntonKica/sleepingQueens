package game.drawing;

import game.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawStrategyFull implements DrawStrategy {
	private final Random random;

	public DrawStrategyFull(int seed) {
		random = new Random(seed);
	}
	@Override
	public List<Card> draw(List<Card> drawingPile, List<Card> trashingPile, int count) {
		if(drawingPile.size() < count) {
			Collections.shuffle(trashingPile, random);
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
