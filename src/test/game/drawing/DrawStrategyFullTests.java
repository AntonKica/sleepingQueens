package game.drawing;

import game.card.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DrawStrategyFullTests {
	private List<Card> initialCards;
	private DrawingAndTrashPile pile;

	@Before
	public void init() {
		initialCards = new ArrayList<>();
		pile = new DrawingAndTrashPile(new ArrayList<>(initialCards), new DrawStrategyFull());
	}
	@Test
	public void drawed_all_cards_and_none_left() {
		var drawedCards = pile.draw(initialCards.size());
		Optional<Card> shouldBeEmptyCard = Optional.empty();
		try {
			shouldBeEmptyCard = Optional.of(pile.draw(1).get(1));
		} catch (Exception ex) {
		}

		Assert.assertNull("Drawed from empty pile", shouldBeEmptyCard.orElse(null));
	}
}
