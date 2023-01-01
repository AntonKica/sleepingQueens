package game.player.helpers;

import game.position.AwokenQueenPosition;
import game.position.SleepingQueenPosition;
import game.queens.AwokenQueens;
import game.queens.Queen;
import game.queens.SleepingQueens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class QueenHelpers {
	public static class two_sleeping_queens_with_value_by_position extends SleepingQueens {

		public two_sleeping_queens_with_value_by_position() {
			super(List.of());
		}

		@Override
		public Map<SleepingQueenPosition, Queen> getQueens() {
			return super.getQueens();
		}

		private Queen test_selected;
		private Queen test_drawn;

		@Override
		public boolean select(SleepingQueenPosition position) {
			if (position.getCardIndex() == 0 && position.getCardIndex() == 1) {
				test_selected = new Queen(position.getCardIndex());
				return true;
			} else {
				return false;
			}
		}

		public void test_reset() {
			test_selected = null;
			test_drawn = null;
		}

		boolean test_selected_correct_queen_index(Integer index) {
			return Objects.equals(test_selected, index);
		}

		public Queen get_test_drawn() {
			return test_drawn;
		}

		@Override
		public Queen draw() {
			test_drawn = test_selected;
			return test_drawn;
		}

	}

	public static class test_awoken_queens extends AwokenQueens {
		private boolean test_has_one;

		public test_awoken_queens(Integer playerIndex, boolean test_has_one) {
			super(playerIndex);
			this.test_has_one = test_has_one;

			test_reset();
		}

		@Override
		public Map<AwokenQueenPosition, Queen> getQueens() {
			return new HashMap<>();
		}

		private Queen test_selected;
		private Queen test_drawn;
		private Queen test_aquired;

		@Override
		public boolean select(AwokenQueenPosition position) {
			if (position.getPlayerIndex().equals(playerIndex) && position.getCardIndex() == 0 && test_has_one) {
				test_selected = new Queen(0);
				return true;
			} else {
				return false;
			}
		}

		public void test_reset() {
			test_selected = null;
			test_drawn = null;
			test_aquired = null;
		}

		@Override
		public Queen draw() {
			if (test_has_one)
				test_drawn = test_selected;
			return test_drawn;
		}

		public Queen get_test_drawn() {
			return test_drawn;
		}

		@Override
		public void addQueen(Queen q) {
			test_aquired = q;
		}

		public Queen get_test_aquired() {
			return test_aquired;
		}
	}
}
