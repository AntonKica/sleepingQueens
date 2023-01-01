package game.player;

import game.card.Card;
import game.card.CardType;
import game.player.BasicHand;
import game.player.Player;
import game.position.HandPosition;
import game.position.Position;
import game.queens.AwokenQueens;
import game.queens.PlayerAwokenQueens;
import game.queens.PlayerSleepingQueens;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerNumberedCardsTests {
	public static class hand_with_numbered_cards extends BasicHand {

		protected hand_with_numbered_cards() {
			super(null, List.of());
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
		public boolean playCardOfType(CardType type) {
			return false;
		}

		public void test_set_cards(List<Card> setCards) {
			cards = new ArrayList<>(setCards);
		}

		private boolean test_picked_cards_were_removed() {
			return cards.isEmpty();
		}
	};

	hand_with_numbered_cards mockedHand;
	Player player;

	@Before
	public void init() {
		mockedHand = new hand_with_numbered_cards();
		var hands = new HashMap<Integer, BasicHand>();
		hands.put(0, mockedHand);

		var sleepingQueen = new PlayerSleepingQueens(new ArrayList<>());

		var awokenQueen = new PlayerAwokenQueens(0);
		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, awokenQueen);

		player = new Player(mockedHand, hands, sleepingQueen, awokenQueen, awokenQueens);
	}


	@Test
	public void player_plays_three_numbered() {
		assertPlayed(List.of(1, 2, 3));
		assertPlayed(List.of(5, 1, 6));
		assertPlayed(List.of(6, 4, 2));
	}

	private void playerPlay(List<Integer> values) {
		var playCards = values.stream().map(val -> new Card(CardType.NUMBER, val)).toList();
		mockedHand.test_set_cards(playCards);

		var handPositions = IntStream.range(0, values.size())
				.mapToObj(idx -> new HandPosition(0,  idx))
				.map(Position.class::cast).toList();
		player.play(handPositions);
	}

	private void assertPlayed(List<Integer> values) {
		playerPlay(values);
		var failMessage = "Did not play numbered " + values.stream().map(v->Integer.toString(v))
				.collect(Collectors.joining(" ", "[", "]"));
		Assert.assertTrue(failMessage, mockedHand.test_picked_cards_were_removed());
	}
	private void assertDidNotPlay(List<Integer> values) {
		playerPlay(values);
		var failMessage = "Did play numbered " + values.stream().map(v->Integer.toString(v))
				.collect(Collectors.joining("[", " ", "]"));
		Assert.assertFalse(failMessage, mockedHand.test_picked_cards_were_removed());
	}

	@Test
	public void player_did_not_play_three_numbered() {
		assertDidNotPlay(List.of(1, 4, 8));
		assertDidNotPlay(List.of(2, 3, 4));
		assertDidNotPlay(List.of(5, 6, 10));
	}


	@Test
	public void player_plays_any_numbered() {
		IntStream.range(1, 11).forEach(i -> assertPlayed(List.of(i)));
	}
}
