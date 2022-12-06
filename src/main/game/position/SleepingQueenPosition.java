package game.position;

import game.position.Position;

public class SleepingQueenPosition implements Position{
private Integer cardIndex;

	public SleepingQueenPosition(Integer cardIndex) {
		this.cardIndex = cardIndex;
	}

	public Integer getCardIndex() {
		return cardIndex;
	}
}
