package game.player;

import game.drawing.DrawingAndTrashPile;
import game.card.Card;
import game.card.CardType;
import game.position.HandPosition;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hand extends BasicHand {
	protected Hand(DrawingAndTrashPile drawingAndTrashPile, List<Card> cards) {
		super(drawingAndTrashPile, cards);
	}

	private Optional<Card> pickCardInt(HandPosition position) {
		var at = position.getCardIndex();
		return (at >= 0  && at < cards.size()) ? Optional.of(cards.get(at)) : Optional.empty();
	}
	@Override
	public Optional<Card> pickCard(HandPosition position) {
		var card = pickCardInt(position);
		card.ifPresent(c -> pickedCards = Arrays.asList(c));

		return card;
	}
	@Override
	public Optional<List<Card>> pickCards(List<HandPosition> positions) {
		var cards = positions.stream().map(this::pickCardInt).flatMap(Optional::stream).collect(Collectors.toList());
		if(cards.size() != positions.size())
			return Optional.empty();

		pickedCards = cards;
		return Optional.of(cards);
	}

	@Override
	public void removePickedCardsAndRedraw() {
		cards.removeAll(pickedCards);
		drawingAndTrashPile.trash(pickedCards);

		cards.addAll(drawingAndTrashPile.draw(pickedCards.size()));
		pickedCards = List.of();
	}

	@Override
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
}
