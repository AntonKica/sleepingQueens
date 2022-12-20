package game;

import game.card.Card;
import game.card.CardType;
import game.player.Hand;
import game.player.Player;
import game.player.PlayerState;
import game.position.AwokenQueenPosition;
import game.position.HandPosition;
import game.position.Position;
import game.position.SleepingQueenPosition;
import game.queens.AwokenQueens;
import game.queens.Queen;
import game.queens.SleepingQueens;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class PlayerTest {
	private DrawingAndTrashPile drawingAndTrashPile;

	@Before
	public void init() {
		drawingAndTrashPile = mock(DrawingAndTrashPile.class);
	}

	public void assertPlayHand(List<Position> handPositions, boolean shouldSucceed, String message) {
		var hand = new Hand(drawingAndTrashPile, List.of(
				new Card(CardType.NUMBER, 1),
				new Card(CardType.NUMBER, 2),
				new Card(CardType.NUMBER, 3),
				new Card(CardType.NUMBER, 4),
				new Card(CardType.NUMBER, 10)));

		var player = new Player(hand,
				null,
				new SleepingQueens(new ArrayList<>()),
				new AwokenQueens(0), null);

		var drawCards = handPositions.stream().map(i->new Card(CardType.NUMBER, -1)).toList();
		when(drawingAndTrashPile.draw(drawCards.size())).thenReturn(drawCards);

		var cardsOld = player.getPlayerState().getCards().values().stream().toList();
		var cardsNew = player.play(handPositions).getCards().values().stream().toList();

		Assert.assertEquals(message, shouldSucceed, !cardsNew.containsAll(cardsOld) && cardsNew.size() == cardsOld.size());
	}
	@Test
	public void testPlayHand() {
		assertPlayHand(
				List.of(
						new HandPosition(0, 0),
						new HandPosition(0, 1),
						new HandPosition(0, 2)),
				true,
				"1 + 2 != 3");

		assertPlayHand(
				List.of(
						new HandPosition(0, 0),
						new HandPosition(0, 1),
						new HandPosition(0, 3)),
				false,
				"1 + 2 == 4");
		assertPlayHand(
				List.of(
						new HandPosition(0, 0),
						new HandPosition(0, 1)),
				false,
				"1 == 2");
		assertPlayHand(
				List.of(
						new HandPosition(0, 0),
						new HandPosition(0, 1),
						new HandPosition(0, 2),
						new HandPosition(0, 3),
						new HandPosition(0, 4)),
		true,
				"1 + 2 + 3 + 4 == 10");
	}

	public void assertPlaySleepingQueen(Card handCard, Position queenPosition, boolean shouldSucceed, String message) {
		var hand = new Hand(drawingAndTrashPile, List.of(handCard));

		var sleepingQueens = new SleepingQueens(List.of(new Queen(10)));
		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, new AwokenQueens(0));
		awokenQueens.put(1, new AwokenQueens(1));

		var player = new Player(hand,
				null,
				sleepingQueens,
				awokenQueens.get(0),
				awokenQueens);

		var oldState = player.getPlayerState();
		var newState = player.play(List.of(new HandPosition(0, 0), queenPosition));
		var oldCards = oldState.getCards().values().stream().toList();
		var newCards = newState.getCards().values().stream().toList();
		var newAwokenQueens = newState.getAwokenQueens();

		Assert.assertEquals(message, shouldSucceed, !oldCards.equals(newCards) && !newAwokenQueens.isEmpty());
	}
	@Test
	public void playSleepingQueen() {
		assertPlaySleepingQueen(
				new Card(CardType.KING, 0),
				new SleepingQueenPosition(0),
				true,
				"Players king did not pick sleeping queen at 0");
		assertPlaySleepingQueen(
				new Card(CardType.KING, 0),
				new SleepingQueenPosition(1),
				false,
				"Players king picked non-existent sleeping queen at 1");
		assertPlaySleepingQueen(
				new Card(CardType.KNIGHT, 0),
				new SleepingQueenPosition(1),
				false,
				"Players knight picked sleeping queen at 0");
		assertPlaySleepingQueen(
				new Card(CardType.KNIGHT, 0),
				new SleepingQueenPosition(1),
				false,
				"Players knight picked non-existent sleeping queen at 1");
		assertPlaySleepingQueen(
				new Card(CardType.KING, 0),
				new AwokenQueenPosition(1, 1),
				false,
				"Players king picked non-existent awoken queen from player 1 at 0");
	}
	@Test
	public void awokenQueen() {
		// Java code for IntStream boxed()
		IntStream stream = IntStream.range(3, 8);

		// Creating a Stream of Integers
		// Using IntStream boxed() to return
		// a Stream consisting of the elements
		// of this stream, each boxed to an Integer.
		Stream<Integer> stream1 = stream.boxed();

		// Displaying the elements
		stream1.map(i->i*i).forEach(System.out::println);


	}
}
