package game.queens;

import game.position.AwokenQueenPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class AwokenQueens extends QueenCollection<AwokenQueenPosition>{
	protected final Integer playerIndex;

	protected AwokenQueens(Integer playerIndex) {
		super(new ArrayList<>());
		this.playerIndex = playerIndex;
	}

	@Override
	public Map<AwokenQueenPosition, Queen> getQueens() {
		var result = new HashMap<AwokenQueenPosition, Queen>();
		IntStream.range(0, queens.size())
				.forEach(i->result.put(new AwokenQueenPosition(playerIndex, i), queens.get(i)));

		return result;
	}

	@Override
	public boolean select(AwokenQueenPosition position) {
		if(!position.getPlayerIndex().equals(playerIndex)
				&& position.getCardIndex() >= 0
				&& position.getCardIndex() < queens.size())
			return false;

		pickedQueen = position.getCardIndex();
		return true;
	}
}