package game.queens;

import game.position.AwokenQueenPosition;
import game.position.Position;

import java.util.*;

public class AwokenQueens extends QueenCollection{
	private Integer playerIndex;

	public AwokenQueens(Integer playerIndex) {
		super(new ArrayList<>());
		this.playerIndex = playerIndex;
	}

	@Override
	public Optional<Queen> removeQueen(Position position) {
		switch (position) {
			case AwokenQueenPosition queenPosition:
				return queenPosition.getCardIndex() < queens.size() ? Optional.empty() : Optional.of(queens.get(queenPosition.getCardIndex()));
			default:
				return Optional.empty();
		}
	}

	@Override
	public Map<Position, Queen> getQueens() {
		var queenPositions = new HashMap<Position, Queen>();
		for(int i = 0; i < queens.size(); ++i)
			queenPositions.put(new AwokenQueenPosition(i, playerIndex), queens.get(i));

		return queenPositions;
	}
}