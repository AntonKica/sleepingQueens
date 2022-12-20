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

	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		else if(other == this)
			return true;
		else if (! (other instanceof Card))
			return false;
		else {
			var otherCard = (Card)other;
			return type == otherCard.type && value == otherCard.value;
		}
	}
}
