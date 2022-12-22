package game.queens;

import game.position.SleepingQueenPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SleepingQueens extends QueenCollection<SleepingQueenPosition> {
	protected SleepingQueens(List<Queen> queens) {
		super(queens);
	}
}
