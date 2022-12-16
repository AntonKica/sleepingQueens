package game.position;

public class HandPosition implements Position {
	private Integer playerIndex;

	private Integer cardIndex;
	public HandPosition(Integer playerIndex, Integer cardIndex) {
		this.playerIndex = playerIndex;
		this.cardIndex = cardIndex;
	}


	public Integer getPlayerIndex() { return playerIndex;
	}
	public int getCardIndex() { return cardIndex; }
}
