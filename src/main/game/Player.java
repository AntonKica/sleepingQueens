package game;

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

	public String getName() {
		return name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void useCardOnPlayer(String cardname, String targetPlayer) {
		var cardFind = cards.stream().filter(find -> find.name.equals(cardname)).findFirst();
		if(cardFind.isEmpty()) return;

		var card = cardFind.get();
		targetPlayer.defendAgainstCard(card);
		consumeCard(card);
	}

	private void consumeCard(Card card) {
	}

	private Card repelKnight(Card queen) {
		var dragonFind = cards.stream().filter(find -> find.name.startsWith("dragon")).findFirst();
		if(dragonFind.isPresent()) {
			consumeCard(dragonFind.get());
			return null;
		} else {
			game.queens.remove(queen);
			return queen;
		}
	}

	private void defendAgainstCard(Card card) {
		if(card.name.startsWith("knight")) {

		} else if (card.name.startsWith("sleeping_potion")) {

		}
	}
}
