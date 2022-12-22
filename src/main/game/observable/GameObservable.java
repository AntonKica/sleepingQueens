package game.observable;

import game.GameState;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameObservable {
	private List<GameObserver> observers;
	private Map<Integer, GameObserver> playerObservers;
	void add(GameObserver observer) {
		observers.add(observer);
	}
	void remove(GameObserver observer) {
		observers.remove(observer);
		playerObservers.values().remove(observer);
	}

	void addPlayer(int playerIndex, GameObserver observer) {
		playerObservers.put(playerIndex, observer);
	}

	void notifyAll(GameState state) {
		var messageBuilder = new StringBuilder();

		messageBuilder.append(state.getNumberOfPlayers());
		messageBuilder.append(" ");
		messageBuilder.append(state.getOnTurn());
		messageBuilder.append(" ");
		messageBuilder.append(state.getSleepingQueens().stream().map(Objects::toString)
				.collect(Collectors.joining(" ", "[ ", " ]")));
		messageBuilder.append(" ");
		messageBuilder.append(state.getCards().entrySet().stream()
				.map(entrySet -> String.format("<%s, %s>", entrySet.getKey(), entrySet.getValue()))
				.collect(Collectors.joining(" ", "[ ", " ]")));
		messageBuilder.append(" ");
		messageBuilder.append(state.getAwokeenQueens().entrySet().stream()
				.map(entrySet -> String.format("<%s, %s>", entrySet.getKey(), entrySet.getValue()))
				.collect(Collectors.joining(" ", "[ ", " ]")));
		messageBuilder.append(" ");
		messageBuilder.append(state.getCardsDiscardedLastTurn().stream().map(Objects::toString)
				.collect(Collectors.joining(" ", "[ ", " ]")));
		messageBuilder.append(" ");

		var message = messageBuilder.toString();

		observers.forEach(o -> o.notify(message));
		playerObservers.values().forEach(o -> o.notify(message));
	}
}
