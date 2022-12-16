package game.player;

import game.card.Card;
import game.card.CardType;
import game.position.AwokenQueenPosition;
import game.position.HandPosition;
import game.position.Position;
import game.position.SleepingQueenPosition;
import game.queens.AwokenQueens;
import game.queens.Queen;
import game.queens.SleepingQueens;

import java.util.*;

public class Player {
	private final Map<Integer, Hand> hands;
	private final Hand myHand;
	private final SleepingQueens sleepingQueens;
	private final Map<Integer, AwokenQueens> awokenQueens;
	private final AwokenQueens myAwokenQueens;


	public Player(
			Hand myHand,
			Map<Integer, Hand> hands,
			SleepingQueens sleepingQueens,
			AwokenQueens myAwokenQueens,
			Map<Integer, AwokenQueens> awokenQueens) {
		this.hands = hands;
		this.myHand = myHand;
		this.sleepingQueens = sleepingQueens;
		this.awokenQueens = awokenQueens;
		this.myAwokenQueens = myAwokenQueens;
	}

	public PlayerState play(List<Position> positions) {
		// TODO Determine which class is responsible for evaluating the play
		if(countHands(positions) == 1 && countAwoken(positions) == 1 && countSleeping(positions) == 0)
			playAwokenQueen(positions);
		else if(countHands(positions) == 1 && countAwoken(positions) == 0 && countSleeping(positions) == 1)
			playSleepingQueen(positions);
		else if(countHands(positions) == positions.size())
			playHand(positions);

		return getPlayerState();
	}


	private int countAwoken(List<Position> positions) {
		return (int)positions.stream().filter(AwokenQueenPosition.class::isInstance).count();
	}
	private int countSleeping(List<Position> positions) {
		return (int)positions.stream().filter(SleepingQueenPosition.class::isInstance).count();
	}

	private int countHands(List<Position> positions) {
		return (int)positions.stream().filter(Hand.class::isInstance).count();
	}

	private void playSleepingQueen(List<Position> positions) {
		if(!sleepingQueens.select((SleepingQueenPosition)positions.get(1)))
			return;

		myHand.pickCard((HandPosition)positions.get(0))
				.filter(c -> c.getType() == CardType.KING)
				.ifPresent(c -> {
					myAwokenQueens.addQueen(sleepingQueens.draw());
					myHand.removePickedCardsAndRedraw();
				});
	}
	private void playAwokenQueen(List<Position> positions) {
		var aqp = (AwokenQueenPosition)positions.get(1);
		var aq = awokenQueens.get(aqp.getPlayerIndex());
		if(!aq.select(aqp))
			return;

		myHand.pickCard((HandPosition)positions.get(0)).ifPresent(c -> {
			var otherHand = hands.get(aqp.getPlayerIndex());
			switch (c.getType()) {
				case SLEEPING_POTION -> {
					if (!otherHand.playCardOfType(CardType.MAGIC_WAND))
						myAwokenQueens.addQueen(aq.draw());
				}
				case KNIGHT -> {
					if (!otherHand.playCardOfType(CardType.DRAGON))
						myAwokenQueens.addQueen(aq.draw());
				}
				case default -> {
					return;
				}
			}
			myHand.removePickedCardsAndRedraw();
		});
	}

	private void playHand(List<Position> positions) {
		myHand.pickCards(positions.stream().map(HandPosition.class::cast).toList()).ifPresent(cards -> {
			if(cards.stream().anyMatch(c -> c.getType() != CardType.NUMBER))
				return;

			cards.sort(Comparator.comparing(Card::getValue));
			var leftSum = cards.subList(0, cards.size() - 1).stream().mapToInt(Card::getValue).sum();
			var rightSum = cards.get(cards.size() - 1).getValue();

			if(leftSum == rightSum)
				myHand.removePickedCardsAndRedraw();
		});
	}

	public PlayerState getPlayerState() {
		var stateCards = new HashMap<Integer, Card>();
		var cards = myHand.getCards();
		for(var i = 0; i < cards.size(); ++i)
			stateCards.put(i, cards.get(1));

		var stateQueens = new HashMap<Integer, Queen>();
		myAwokenQueens.getQueens().forEach((p, q) -> stateQueens.put(p.getCardIndex(), q));

		return new PlayerState(stateCards, stateQueens);
	}
}
