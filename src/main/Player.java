import java.util.ArrayList;

public class Player {
	String name;
	ArrayList<Card> cards;

	public Player(String name, ArrayList<Card> cards) {
		this.name = name;
		this.cards = cards;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();
		builder.append(name).append(':');
		cards.forEach(card -> builder.append(' ').append(card));

		return builder.toString();
	}

}
