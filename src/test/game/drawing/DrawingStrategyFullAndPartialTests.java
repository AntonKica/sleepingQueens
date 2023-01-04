package game.drawing;

import game.card.Card;
import game.card.CardType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class DrawingStrategyFullAndPartialTests {
	private DrawingAndTrashPile pileWithFullDrawingStrategy;
	private DrawingAndTrashPile pileWithPartialDrawingStrategy;

	@Before
	public void init() {
		var initialCardsWithFiveNumbered = new ArrayList<Card>();
		IntStream.range(0, 5).forEach(i->initialCardsWithFiveNumbered.add(new Card(CardType.NUMBER, i)));

		pileWithFullDrawingStrategy = new DrawingAndTrashPile(new ArrayList<>(initialCardsWithFiveNumbered),
				new DrawStrategyFull(0));
		pileWithPartialDrawingStrategy = new DrawingAndTrashPile(new ArrayList<>(initialCardsWithFiveNumbered),
				new DrawStrategyPartial(0));
	}

	Set<Card> test_draw_four_trash_and_draw_three(DrawingAndTrashPile pile) {
		var twoCards = pile.draw(4);
		pile.trash(twoCards);
		return new HashSet<>(pile.draw(3));
	}
	@Test
	public void drawed_all_same_cards_with_full_and_partial() {
		var fullDrawingStrategyCards = test_draw_four_trash_and_draw_three(pileWithFullDrawingStrategy);
		var partialDrawingStrategyCards = test_draw_four_trash_and_draw_three(pileWithPartialDrawingStrategy);

		var message = "Drawed different cards\n Full: " + fullDrawingStrategyCards + "\n" + partialDrawingStrategyCards;
		Assert.assertTrue(message,
				fullDrawingStrategyCards.containsAll(partialDrawingStrategyCards)
				&& partialDrawingStrategyCards.containsAll(fullDrawingStrategyCards));
	}
}
