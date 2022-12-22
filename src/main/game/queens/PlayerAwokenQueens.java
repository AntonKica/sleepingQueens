package game.queens;

import game.position.AwokenQueenPosition;

import java.util.*;

public class PlayerAwokenQueens extends AwokenQueens {

	public PlayerAwokenQueens(Integer playerIndex) {
		super(playerIndex);
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
		if(position.getPlayerIndex() != playerIndex ||
				position.getCardIndex() < 0 ||
				position.getCardIndex() >= queens.size())
			return false;

		pickedQueen = position.getCardIndex();
		return true;
	}
}