package game.queens;

import game.position.Position;
import game.position.SleepingQueenPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SleepingQueens extends QueenCollection<SleepingQueenPosition> {
	public SleepingQueens(List<Queen> queens) {
		super(queens);
	}


	@Override
	public Map<SleepingQueenPosition, Queen> getQueens() {
		var queenPositions = new HashMap<SleepingQueenPosition, Queen>();
		for(int i = 0; i < queens.size(); ++i)
			queenPositions.put(new SleepingQueenPosition(i), queens.get(i));

		return queenPositions;
	}

	@Override
	public boolean select(SleepingQueenPosition position) {
		if(position.getCardIndex() < 0 ||
				position.getCardIndex() >= queens.size())
			return false;

		pickedQueen = position.getCardIndex();
		return true;
	}
}
