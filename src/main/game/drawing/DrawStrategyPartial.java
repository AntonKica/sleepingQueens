package game.drawing;

import game.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawStrategyPartial implements DrawStrategy {
	private final Random random;

	public DrawStrategyPartial(int seed) {
		random = new Random(seed);
	}

	private List<Card> drawPartial(List<Card> drawingPile, int count) {
		count = Integer.min(drawingPile.size(), count);

		var drawCardsTemp = drawingPile.subList(0, count);
		var drawCards = new ArrayList<>(drawCardsTemp);
		drawCardsTemp.clear();

		return drawCards;
	}
	@Override
	public List<Card> draw(List<Card> drawingPile, List<Card> trashingPile, int count) {
		var cards = drawPartial(drawingPile, count);
		if(cards.size() != count) {
			var remainingCount = count - cards.size();

			Collections.shuffle(trashingPile, random);
			drawingPile.addAll(trashingPile);
			trashingPile.clear();

			cards.addAll(drawPartial(drawingPile, remainingCount));

			if(cards.size() != count)
				throw new RuntimeException("welp");
		}

		return cards;
	}
}
