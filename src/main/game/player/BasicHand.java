package game.player;

import game.card.Card;
import game.card.CardType;
import game.drawing.DrawingAndTrashPile;
import game.position.HandPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BasicHand {
	protected DrawingAndTrashPile drawingAndTrashPile;
	protected List<Card> cards;
	protected List<Card> pickedCards;
	protected BasicHand(DrawingAndTrashPile drawingAndTrashPile, List<Card> cards) {
		this.drawingAndTrashPile = drawingAndTrashPile;
		this.cards = new ArrayList<>(cards);
		this.pickedCards = new ArrayList<>();
	}
	public List<Card> getCards() {
		return cards;
	}
	public abstract Optional<Card> pickCard(HandPosition position);
	public abstract Optional<List<Card>> pickCards(List<HandPosition> positions);
	public abstract void removePickedCardsAndRedraw();

	public abstract boolean playCardOfType(CardType type);
}
