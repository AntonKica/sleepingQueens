package game.queens;

import game.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class QueenCollection {
	protected List<Queen> queens;

	public QueenCollection(List<Queen> queens) {
		this.queens = queens;
	}
	private void addQueen(Queen queen) {
		queens.add(queen);
	}

	public abstract Optional<Queen> removeQueen(Position position);
	public abstract Map<Position, Queen> getQueens();
}
