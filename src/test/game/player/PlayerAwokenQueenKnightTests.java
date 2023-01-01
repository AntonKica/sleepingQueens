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

public class PlayerAwokenQueenKnightTests {
	HandHelpers.hand_with_special_type_or_not mockedHandWithOrWithoutKnight;
	HandHelpers.hand_with_special_type_or_not mockedHandWithOrWithoutDragon;
	QueenHelpers.test_awoken_queens awokenQueensHandWithOrWithoutKnight;
	QueenHelpers.test_awoken_queens awokenQueensHandWithOrWithoutDragon;
	Player playerWithOrWithoutKnight, getPlayerWithOrWithoutDragon;

	@Before
	public void init() {
		mockedHandWithOrWithoutKnight = new HandHelpers.hand_with_special_type_or_not(CardType.KNIGHT);
		mockedHandWithOrWithoutDragon = new HandHelpers.hand_with_special_type_or_not(CardType.DRAGON);

		var hands = new HashMap<Integer, BasicHand>();
		hands.put(0, mockedHandWithOrWithoutKnight);
		hands.put(1, mockedHandWithOrWithoutDragon);

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

		awokenQueensHandWithOrWithoutKnight = new QueenHelpers.test_awoken_queens(0, false);
		awokenQueensHandWithOrWithoutDragon = new QueenHelpers.test_awoken_queens(1, true);
		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, awokenQueensHandWithOrWithoutKnight);
		awokenQueens.put(1, awokenQueensHandWithOrWithoutDragon);

		playerWithOrWithoutKnight = new Player(
				mockedHandWithOrWithoutKnight,
				hands,
				sleepingQueen,
				awokenQueensHandWithOrWithoutKnight,
				awokenQueens);
		getPlayerWithOrWithoutDragon = new Player(
				mockedHandWithOrWithoutDragon,
				hands,
				sleepingQueen,
				awokenQueensHandWithOrWithoutDragon,
				awokenQueens);
	}


	private void playerPlay(boolean putKnight, boolean putDragon) {
		mockedHandWithOrWithoutKnight.test_put_card(putKnight);
		mockedHandWithOrWithoutDragon.test_put_card(putDragon);

		playerWithOrWithoutKnight.play(List.of(new HandPosition(0, 0), new AwokenQueenPosition(1, 0)));
	}
	@Test
	public void player_plays_with_knight_against_no_dragon() {
		playerPlay(true, false);

		Assert.assertTrue("Did not play knight", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertNotNull("Did not get queen", awokenQueensHandWithOrWithoutKnight.get_test_aquired());
		Assert.assertNotNull("Did not loose queen", awokenQueensHandWithOrWithoutDragon.get_test_drawn());
	}

	@Test
	public void player_plays_with_knight_against_dragon() {
		playerPlay(true, true);

		Assert.assertTrue("Did not play knight", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertTrue("Did not play dragon", mockedHandWithOrWithoutDragon.test_card_was_removed());
		Assert.assertNull("Did get queen", awokenQueensHandWithOrWithoutKnight.get_test_aquired());
		Assert.assertNull("Did loose queen", awokenQueensHandWithOrWithoutDragon.get_test_drawn());
	}

	@Test
	public void player_plays_with_no_knight_against_dragon() {
		playerPlay(false, true);

		Assert.assertFalse("Did play card", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertFalse("Did play dragon", mockedHandWithOrWithoutDragon.test_card_was_removed());
		Assert.assertNull("Did get queen", awokenQueensHandWithOrWithoutKnight.get_test_aquired());
		Assert.assertNull("Did loose queen", awokenQueensHandWithOrWithoutDragon.get_test_drawn());
	}
	@Test
	public void player_plays_with_no_knight_no_dragon() {
		playerPlay(false, false);

		Assert.assertFalse("Did play card", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertFalse("Did play dragon", mockedHandWithOrWithoutDragon.test_card_was_removed());
		Assert.assertNull("Did get queen", awokenQueensHandWithOrWithoutKnight.get_test_aquired());
		Assert.assertNull("Did loose queen", awokenQueensHandWithOrWithoutDragon.get_test_drawn());
	}
}
