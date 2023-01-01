package game.player;

import game.card.CardType;
import game.player.helpers.HandHelpers;
import game.player.helpers.QueenHelpers;
import game.position.AwokenQueenPosition;
import game.position.HandPosition;
import game.position.SleepingQueenPosition;
import game.queens.AwokenQueens;
import game.queens.Queen;
import game.queens.SleepingQueens;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class PlayerAwokenQueenSleepingPotionTests {
	HandHelpers.hand_with_special_type_or_not mockedHandWithOrWithoutSleepingPotion;
	HandHelpers.hand_with_special_type_or_not mockedHandWithOrWithoutMagicWang;
	QueenHelpers.test_awoken_queens awokenQueensHandWithOrWithoutSleepingPotion;
	QueenHelpers.test_awoken_queens awokenQueensHandWithOrWithoutMagicWang;
	Player playerWithOrWithoutSleepingPotion, getPlayerWithOrWithoutMagicWang;

	@Before
	public void init() {
		mockedHandWithOrWithoutSleepingPotion = new HandHelpers.hand_with_special_type_or_not(CardType.SLEEPING_POTION);
		mockedHandWithOrWithoutMagicWang = new HandHelpers.hand_with_special_type_or_not(CardType.MAGIC_WAND);

		var hands = new HashMap<Integer, BasicHand>();
		hands.put(0, mockedHandWithOrWithoutSleepingPotion);
		hands.put(1, mockedHandWithOrWithoutMagicWang);

		var sleepingQueen = new SleepingQueens(List.of()) {
			@Override
			public Map<SleepingQueenPosition, Queen> getQueens() {
				return null;
			}

			@Override
			public boolean select(SleepingQueenPosition position) {
				return false;
			}
		};

		awokenQueensHandWithOrWithoutSleepingPotion = new QueenHelpers.test_awoken_queens(0, false);
		awokenQueensHandWithOrWithoutMagicWang = new QueenHelpers.test_awoken_queens(1, true);
		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, awokenQueensHandWithOrWithoutSleepingPotion);
		awokenQueens.put(1, awokenQueensHandWithOrWithoutMagicWang);

		playerWithOrWithoutSleepingPotion = new Player(
				mockedHandWithOrWithoutSleepingPotion,
				hands,
				sleepingQueen,
				awokenQueensHandWithOrWithoutSleepingPotion,
				awokenQueens);
		getPlayerWithOrWithoutMagicWang = new Player(
				mockedHandWithOrWithoutMagicWang,
				hands,
				sleepingQueen,
				awokenQueensHandWithOrWithoutMagicWang,
				awokenQueens);
	}


	private void playerPlay(boolean putSleepingPotion, boolean putMagicWang) {
		mockedHandWithOrWithoutSleepingPotion.test_put_card(putSleepingPotion);
		mockedHandWithOrWithoutMagicWang.test_put_card(putMagicWang);

		playerWithOrWithoutSleepingPotion.play(List.of(new HandPosition(0, 0), new AwokenQueenPosition(1, 0)));
	}
	@Test
	public void player_plays_with_knight_against_no_dragon() {
		playerPlay(true, false);

		Assert.assertTrue("Did not play knight", mockedHandWithOrWithoutSleepingPotion.test_card_was_removed());
		Assert.assertNotNull("Did not get queen", awokenQueensHandWithOrWithoutSleepingPotion.get_test_aquired());
		Assert.assertNotNull("Did not loose queen", awokenQueensHandWithOrWithoutMagicWang.get_test_drawn());
	}

	@Test
	public void player_plays_with_knight_against_dragon() {
		playerPlay(true, true);

		Assert.assertTrue("Did not play knight", mockedHandWithOrWithoutSleepingPotion.test_card_was_removed());
		Assert.assertTrue("Did not play dragon", mockedHandWithOrWithoutMagicWang.test_card_was_removed());
		Assert.assertNull("Did get queen", awokenQueensHandWithOrWithoutSleepingPotion.get_test_aquired());
		Assert.assertNull("Did loose queen", awokenQueensHandWithOrWithoutMagicWang.get_test_drawn());
	}

	@Test
	public void player_plays_with_no_knight_against_dragon() {
		playerPlay(false, true);

		Assert.assertFalse("Did play card", mockedHandWithOrWithoutSleepingPotion.test_card_was_removed());
		Assert.assertFalse("Did play dragon", mockedHandWithOrWithoutMagicWang.test_card_was_removed());
		Assert.assertNull("Did get queen", awokenQueensHandWithOrWithoutSleepingPotion.get_test_aquired());
		Assert.assertNull("Did loose queen", awokenQueensHandWithOrWithoutMagicWang.get_test_drawn());
	}
	@Test
	public void player_plays_with_no_knight_no_dragon() {
		playerPlay(false, false);

		Assert.assertFalse("Did play card", mockedHandWithOrWithoutSleepingPotion.test_card_was_removed());
		Assert.assertFalse("Did play dragon", mockedHandWithOrWithoutMagicWang.test_card_was_removed());
		Assert.assertNull("Did get queen", awokenQueensHandWithOrWithoutSleepingPotion.get_test_aquired());
		Assert.assertNull("Did loose queen", awokenQueensHandWithOrWithoutMagicWang.get_test_drawn());
	}
}
