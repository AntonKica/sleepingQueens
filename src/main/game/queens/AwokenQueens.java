package game.queens;

import game.position.AwokenQueenPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AwokenQueens extends QueenCollection<AwokenQueenPosition>{
	protected final Integer playerIndex;

	protected AwokenQueens(Integer playerIndex) {
		super(new ArrayList<>());
		this.playerIndex = playerIndex;
	}
}