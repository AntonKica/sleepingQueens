package game.queens;

import game.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class QueenCollection<PositionType extends Position> {
	protected List<Queen> queens;
	protected Integer pickedQueen;

	public QueenCollection(List<Queen> queens) {
		this.queens = new ArrayList<>(queens);
		this.pickedQueen = null;
	}
	public void addQueen(Queen queen) {
		queens.add(queen);
	}

	public abstract Map<PositionType, Queen> getQueens();

	public abstract boolean select(PositionType position);
	public Queen draw() {
		var ret = queens.get(pickedQueen);
		queens.remove(ret);
		pickedQueen = null;

		return ret;
	}
}
