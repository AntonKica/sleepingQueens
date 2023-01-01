package game.player;

import game.card.CardType;
import game.position.HandPosition;
import game.position.SleepingQueenPosition;
import game.queens.AwokenQueens;
import game.queens.PlayerAwokenQueens;
import game.queens.Queen;
import game.queens.SleepingQueens;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class PlayerSleepingQueenTests {
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

	HandHelperClasses.hand_with_special_type_or_not mockedHandWithOrWithoutKing;
	two_sleeping_queens_with_value_by_position sleepingQueen;
	Player player;

	@Before
	public void init() {
		mockedHandWithOrWithoutKing = new HandHelperClasses.hand_with_special_type_or_not(CardType.KING);
		var hands = new HashMap<Integer, BasicHand>();
		hands.put(0, mockedHandWithOrWithoutKing);

		sleepingQueen = new two_sleeping_queens_with_value_by_position();

		var awokenQueen = new PlayerAwokenQueens(0);
		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, awokenQueen);

		player = new Player(mockedHandWithOrWithoutKing, hands, sleepingQueen, awokenQueen, awokenQueens);
	}


	@Test
	public void player_plays_king_with_correct_queen() {
		assertPlayedAndCorrect(0);
		assertPlayedAndCorrect(1);
	}

	private void playerPlay(boolean putKing, int queenIndex) {
		mockedHandWithOrWithoutKing.test_put_card(putKing);
		sleepingQueen.test_reset_queens();

		player.play(List.of(new HandPosition(0, 0), new SleepingQueenPosition(queenIndex)));
	}
	private void assertPlayedAndCorrect(int queenIndex) {
		playerPlay(true, queenIndex);

		Assert.assertTrue("Did not play king", mockedHandWithOrWithoutKing.test_card_was_removed());
		Assert.assertTrue("Did not get correct queen", sleepingQueen.test_selected_correct_queen_index(queenIndex));
	}
	private void assertDidNotPlay(int queenIndex) {
		playerPlay(false, queenIndex);
		Assert.assertFalse("Did play king", mockedHandWithOrWithoutKing.test_card_was_removed());
		Assert.assertFalse("Did get queen", sleepingQueen.test_selected_correct_queen_index(null));
	}

	@Test
	public void player_did_not_play_with_incorrect_card() {
		assertDidNotPlay(0);
		assertDidNotPlay(1);
	}
}
