package game.position;

public class AwokenQueenPosition implements Position{
	private Integer playerIndex;
	private Integer cardIndex;

	public AwokenQueenPosition(Integer playerIndex, Integer cardIndex) {
		this.playerIndex = playerIndex;
		this.cardIndex = cardIndex;
	}

	public Integer getPlayerIndex() {
		return playerIndex;
	}
	public Integer getCardIndex() {
		return cardIndex;
	}
}
