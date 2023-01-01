package game.player;

import game.card.CardType;
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
	public static class two_sleeping_queens_with_value_by_position extends SleepingQueens {

		protected two_sleeping_queens_with_value_by_position() {
			super(List.of());
		}

		public void test_reset_queens() {
			queens = new ArrayList<>(List.of(new Queen(0), new Queen(1)));
		}

		@Override
		public Map<SleepingQueenPosition, Queen> getQueens() {
			var map = new HashMap<SleepingQueenPosition, Queen>();
			queens.stream().forEach(q -> map.put(new SleepingQueenPosition(0), queens.get(0)));

			return null;
		}

		private Integer test_pickedQueenIndex;
		@Override
		public boolean select(SleepingQueenPosition position) {
			if(position.getCardIndex() < queens.size() && position.getCardIndex() >= 0) {
				pickedQueen = position.getCardIndex();
				test_pickedQueenIndex = queens.get(pickedQueen).getPoints();
				return true;
			} else {
				pickedQueen = null;
				test_pickedQueenIndex = null;
				return false;
			}
		}

		boolean test_selected_correct_queen_index(Integer index) {
			return Objects.equals(test_pickedQueenIndex, index);
		}
	}

	static class test_awoken_queens extends AwokenQueens {
		private boolean addOne;
		protected test_awoken_queens(Integer playerIndex, boolean addOne) {
			super(playerIndex);
			this.addOne = addOne;

			test_reset();
		}

		@Override
		public Map<AwokenQueenPosition, Queen> getQueens() {
			var queenPositions = new HashMap<AwokenQueenPosition, Queen>();
			for(int i = 0; i < queens.size(); ++i)
				queenPositions.put(new AwokenQueenPosition(i, playerIndex), queens.get(i));

			return queenPositions;
		}

		@Override
		public boolean select(AwokenQueenPosition position) {
			if(position.getPlayerIndex() == playerIndex && position.getCardIndex() < queens.size() && position.getCardIndex() >= 0) {
				pickedQueen = position.getCardIndex();
				return true;
			}  else {
				pickedQueen = null;
				return false;
			}
		}

		public void test_reset(){
			queens.clear();
			if(addOne)
				addQueen(new Queen(0));
		}

		public boolean test_hasQueen() {
			return !queens.isEmpty();
		}
	}

	HandHelperClasses.hand_with_special_type_or_not mockedHandWithOrWithoutKnight;
	HandHelperClasses.hand_with_special_type_or_not mockedHandWithOrWithoutDragon;
	test_awoken_queens awokenQueensHandWithOrWithoutKnight;
	test_awoken_queens awokenQueensHandWithOrWithoutDragon;
	Player playerWithOrWithoutKnight, getPlayerWithOrWithoutDragon;

	@Before
	public void init() {
		mockedHandWithOrWithoutKnight = new HandHelperClasses.hand_with_special_type_or_not(CardType.KNIGHT);
		mockedHandWithOrWithoutDragon = new HandHelperClasses.hand_with_special_type_or_not(CardType.DRAGON);

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

		awokenQueensHandWithOrWithoutKnight = new test_awoken_queens(0, false);
		awokenQueensHandWithOrWithoutDragon = new test_awoken_queens(1, true);
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
		Assert.assertTrue("Did not get queen", awokenQueensHandWithOrWithoutKnight.test_hasQueen());
		Assert.assertFalse("Did not loose queen", awokenQueensHandWithOrWithoutDragon.test_hasQueen());
	}

	@Test
	public void player_plays_with_knight_against_dragon() {
		playerPlay(true, true);

		Assert.assertTrue("Did not play knight", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertTrue("Did not play dragon", mockedHandWithOrWithoutDragon.test_card_was_removed());
		Assert.assertFalse("Did get queen", awokenQueensHandWithOrWithoutKnight.test_hasQueen());
		Assert.assertTrue("Did loose queen", awokenQueensHandWithOrWithoutDragon.test_hasQueen());
	}

	@Test
	public void player_plays_with_no_knight_against_dragon() {
		playerPlay(false, true);

		Assert.assertFalse("Did play card", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertFalse("Did play dragon", mockedHandWithOrWithoutDragon.test_card_was_removed());
		Assert.assertFalse("Did get queen", awokenQueensHandWithOrWithoutKnight.test_hasQueen());
		Assert.assertTrue("Did loose queen", awokenQueensHandWithOrWithoutDragon.test_hasQueen());
	}
	@Test
	public void player_plays_with_no_knight_no_dragon() {
		playerPlay(false, false);

		Assert.assertFalse("Did play card", mockedHandWithOrWithoutKnight.test_card_was_removed());
		Assert.assertFalse("Did play dragon", mockedHandWithOrWithoutDragon.test_card_was_removed());
		Assert.assertFalse("Did get queen", awokenQueensHandWithOrWithoutKnight.test_hasQueen());
		Assert.assertTrue("Did loose queen", awokenQueensHandWithOrWithoutDragon.test_hasQueen());
	}
}
