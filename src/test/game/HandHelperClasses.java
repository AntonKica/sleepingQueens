package game;

import game.card.Card;
import game.card.CardType;
import game.player.BasicHand;
import game.position.HandPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HandHelperClasses {

	public static class hand_with_special_type_or_not extends BasicHand {
		private CardType specialType;
		public hand_with_special_type_or_not(CardType type) {
			super(null, List.of());
			specialType = type;
		}

		@Override
		public Optional<Card> pickCard(HandPosition position) {
			var card = cards.get(position.getCardIndex());
			return card == null ? Optional.empty() : Optional.of(card);
		}

		@Override
		public Optional<List<Card>> pickCards(List<HandPosition> positions) {
			var res = positions.stream().map(this::pickCard).flatMap(Optional::stream).toList();
			return res.size() == positions.size() ? Optional.of(new ArrayList<>(res)) : Optional.empty();
		}
		@Override
		public void removePickedCardsAndRedraw() {
			cards.clear();
		}

		@Override
		public boolean playCardOfType(game.card.CardType type) {
			if(cards.get(0).getType() == type) {
				removePickedCardsAndRedraw();
				return true;
			} else {
				return false;
			}
		}
		public void test_put_card(boolean put) {
			cards = new ArrayList<>(List.of(put ? new Card(specialType, 0) : new Card(CardType.NUMBER, 0)));
		}
		public boolean test_card_was_removed() {
			return cards.isEmpty();
		}
	}
}
