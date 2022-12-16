package game;

import game.player.Player;
import game.position.AwokenQueenPosition;
import game.position.HandPosition;
import game.position.Position;
import game.position.SleepingQueenPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class GameAdaptor implements GamePlayerInterface {
	private Game game;
	private List<String> players;

	private final Pattern patternNumberedCards = Pattern.compile("h([:digit:])+");
	private final Pattern patternSleepingQueen = Pattern.compile("h([:digit:]) s([:digit:])");
	private final Pattern patternAwokenQueen = Pattern.compile("h([:digit:]) a([:digit:])([:digit:])");
	GameAdaptor(Game game, List<String> players) {
		this.game = game;
		this.players = players;
	}

	@Override
	public String play(String playerString, String cardsString) {
		var playerIndex = players.indexOf(playerString);
		var cards = new ArrayList<Position>();

		var matchNumberedCards = patternNumberedCards.matcher(cardsString);
		var matchSleepingQueen = patternSleepingQueen.matcher(cardsString);
		var matchAwokenQueen = patternAwokenQueen.matcher(cardsString);

		if(matchNumberedCards.matches()) {
			IntStream.range(0, matchNumberedCards.groupCount()).
					forEach(i -> new HandPosition(playerIndex, Integer.valueOf(matchNumberedCards.group(i))));
		} else if(matchSleepingQueen.matches()) {
			cards.add(new HandPosition(playerIndex, Integer.valueOf(matchNumberedCards.group(0))));
			cards.add(new SleepingQueenPosition(Integer.valueOf(matchNumberedCards.group(1))));;
		} else if(matchAwokenQueen.matches()) {
			cards.add(new HandPosition(playerIndex, Integer.valueOf(matchNumberedCards.group(0))));
			cards.add(new AwokenQueenPosition(
					Integer.valueOf(matchNumberedCards.group(1)),
					Integer.valueOf(matchNumberedCards.group(2))));
		} else {
			return "";
		}

		game.play(playerIndex, cards);

		return "";
	}
}
