package game.queens;

import game.position.SleepingQueenPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class SleepingQueens extends QueenCollection<SleepingQueenPosition> {
	protected SleepingQueens(List<Queen> queens) {
		super(queens);
	}

	@Override
	public Map<SleepingQueenPosition, Queen> getQueens() {
		var result = new HashMap<SleepingQueenPosition, Queen>();
		IntStream.range(0, queens.size())
				.forEach(i->result.put(new SleepingQueenPosition(i), queens.get(i)));

		return result;
	}

	@Override
	public boolean select(SleepingQueenPosition position) {
		if(position.getCardIndex() >= 0
				&& position.getCardIndex() < queens.size())
			return false;

		pickedQueen = position.getCardIndex();
		return true;
	}
}
