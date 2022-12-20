package game.player;

import game.DrawingAndTrashPile;
import game.card.Card;
import game.card.CardType;
import game.position.HandPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hand {
	private DrawingAndTrashPile drawingAndTrashPile;
	private List<Card> cards;
	private List<Card> pickedCards;

	public Hand(DrawingAndTrashPile drawingAndTrashPile, List<Card> cards) {
		this.drawingAndTrashPile = drawingAndTrashPile;
		this.cards = new ArrayList<>(cards);
		this.pickedCards = List.of();
	}

	private Optional<Card> pickCardInt(HandPosition position) {
		var at = position.getCardIndex();
		return (at >= 0  && at < cards.size()) ? Optional.of(cards.get(at)) : Optional.empty();
	}
	public Optional<Card> pickCard(HandPosition position) {
		var card = pickCardInt(position);
		card.ifPresent(c -> pickedCards = Arrays.asList(c));

		return card;
	}
	public Optional<List<Card>> pickCards(List<HandPosition> positions) {
		var cards = positions.stream().map(this::pickCardInt).flatMap(Optional::stream).collect(Collectors.toList());
		if(cards.size() != positions.size())
			return Optional.empty();

		pickedCards = cards;
		return Optional.of(cards);
	}

	public void removePickedCardsAndRedraw() {
		cards.removeAll(pickedCards);
		drawingAndTrashPile.trash(pickedCards);

		cards.addAll(drawingAndTrashPile.draw(pickedCards.size()));
		pickedCards = List.of();
	}

	public boolean playCardOfType(CardType type) {
		var card = cards.stream().filter(c -> c.getType() == type).findFirst();
		if(card.isPresent()) {
				pickedCards = List.of(card.get());
				removePickedCardsAndRedraw();
				return true;
		} else {
			return false;
		}
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
