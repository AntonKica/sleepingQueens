package game.handdrawing;

import game.card.Card;
import game.card.CardType;
import game.drawing.DrawStrategy;
import game.drawing.DrawingAndTrashPile;
import game.player.Hand;
import game.position.HandPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HandDrawingTests {

	static class test_dummy_drawing_strategy_draw_all_count implements DrawStrategy {

		@Override
		public List<Card> draw(List<Card> drawingPile, List<Card> trashingPile, int count) {
			var sublist = drawingPile.subList(0, count);
			var res = new ArrayList<>(sublist);
			sublist.clear();

			return res;
		}
	}
	game.player.Hand hand;
	game.drawing.DrawingAndTrashPile pile;

	List<Card> initialPileCardsOfFive;
	List<Card> initialPlayerCardsOfFive;

	@Before
	public void init() {
		initialPileCardsOfFive = new ArrayList<>();
		IntStream.range(0, 5).forEach(i-> initialPileCardsOfFive.add(new Card(CardType.NUMBER, i)));
		pile = new DrawingAndTrashPile(new ArrayList<>(initialPileCardsOfFive),
				new test_dummy_drawing_strategy_draw_all_count());

		initialPlayerCardsOfFive = new ArrayList<>();
		IntStream.range(5, 10).forEach(i-> initialPlayerCardsOfFive.add(new Card(CardType.NUMBER, i)));
		hand = new Hand(pile, new ArrayList<>(initialPileCardsOfFive));
	}
	@Test
	public void hand_drew_correct_ammount_from_pile() {
		var handPositions = new ArrayList<HandPosition>();
		for(var i = 0; i < initialPileCardsOfFive.size(); ++i)
			handPositions.add(new HandPosition(0, i));

		var pickedCards = hand.pickCards(handPositions);
		Assert.assertTrue("Failed to pick cards", pickedCards.isPresent());

		hand.removePickedCardsAndRedraw();
		var newPlayerCards = hand.getCards();
		Assert.assertFalse("Failed to pick cards from pile", newPlayerCards.isEmpty());

		newPlayerCards.removeAll(initialPileCardsOfFive);
		Assert.assertTrue("Failed to pick pile cards, picked instead: " + newPlayerCards, newPlayerCards.isEmpty());
	}
}
