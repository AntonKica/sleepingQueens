package game.player;

import game.card.Card;
import game.card.CardType;
import game.position.AwokenQueenPosition;
import game.position.Position;
import game.position.SleepingQueenPosition;
import game.queens.AwokenQueens;
import game.queens.SleepingQueens;

import java.util.List;
import java.util.Map;

public class Player {
private Hand hand;
private Map<Integer, Map<CardType, EvaluateAttack>> evaluateAttacks;
private AwokenQueens queens;

	public Player(Hand hand, Map<Integer, Map<CardType, EvaluateAttack>> evaluateAttacks, AwokenQueens queens) {
		this.hand = hand;
		this.evaluateAttacks = evaluateAttacks;
		this.queens = queens;
	}

	public PlayerState play(List<Position> positions) {
		// TODO Determine which class is responsible for evaluating the play
		if(countHands(positions) == 1 && countAwoken(positions) == 1 && countSleeping(positions) == 0)
			return playAwokenQueen(positions);
		else if(countHands(positions) == 1 && countAwoken(positions) == 0 && countSleeping(positions) == 1)
			return playSleepingQueen(positions);
		else if(countHands(positions) == positions.size())
			return playHand(positions);
		else
			return null;
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

	private PlayerState playSleepingQueen(List<Position> positions) {
	}
	private PlayerState playAwokenQueen(List<Position> positions) {
	}
	private PlayerState playHand(List<Position> positions) {
	}
	public PlayerState getPlayerState() {
		// TODO
		return null;
	}
}
