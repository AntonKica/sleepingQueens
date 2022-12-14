package game.card;

public class Card {
	private CardType type;
	private Integer value;

	public Card(CardType type, Integer value) {
		this.type = type;
		this.value = value;
	}

	public CardType getType() {
		return type;
	}

	public Integer getValue() {
		return value;
	}
}
