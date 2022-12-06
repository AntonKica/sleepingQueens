package game.queens;

import game.position.Position;
import game.position.SleepingQueenPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SleepingQueens extends QueenCollection{
	public SleepingQueens(List<Queen> queens) {
		super(queens);
	}

	@Override
	public Optional<Queen> removeQueen(Position position) {
		switch (position) {
			case SleepingQueenPosition queenPosition:
				return queenPosition.getCardIndex() < queens.size() ? Optional.empty() : Optional.of(queens.get(queenPosition.getCardIndex()));
			default:
				return Optional.empty();
		}
	}

	@Override
	public Map<Position, Queen> getQueens() {
		var queenPositions = new HashMap<Position, Queen>();
		for(int i = 0; i < queens.size(); ++i)
			queenPositions.put(new SleepingQueenPosition(i), queens.get(i));

		return queenPositions;
	}
}
