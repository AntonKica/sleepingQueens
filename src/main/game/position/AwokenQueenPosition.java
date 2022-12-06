package game.position;

public class AwokenQueenPosition implements Position{
	private Integer cardIndex;
	private Integer playerIndex;

	public AwokenQueenPosition(Integer cardIndex, Integer playerIndex) {
		this.cardIndex = cardIndex;
		this.playerIndex = playerIndex;
	}

	public Integer getCardIndex() {
		return cardIndex;
	}

	public Integer getPlayerIndex() {
		return playerIndex;
	}
}
