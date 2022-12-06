package game;

import java.util.*;

public class Main {
	private static final int CARD_COUNT_QUEEN = 12;
	private static final int CARD_COUNT_KING = 8;
	private static final int CARD_COUNT_KNIGHT = 4;
	private static final int CARD_COUNT_SLEEPING_POTION = 4;
	private static final int CARD_COUNT_WAND = 3;
	private static final int CARD_COUNT_DRAGON = 3;
	private static final int CARD_COUNT_REGULAR = 10;
	private static final int PLAYER_COUNT = 4;
	private static final int PLAYER_CARD_COUNT = 4;

	static Scanner in;
	static ArrayList<Card> normalCards;
	static ArrayList<Card> queenCards;
	static ArrayList<Player> players;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		initNormalCards();
		initQueenCards();
		initPlayers();

		play();

		System.out.println("\tNormal cards");
		for(var card : normalCards)
			System.out.print(" " + card);
		System.out.println('\n');

		System.out.println("\tQueen cards");
		for(var card : queenCards)
			System.out.print(" " + card);
		System.out.println('\n');

		System.out.println("\tPlayers");
		for(var player : players)
			System.out.println(player );
		System.out.println('\n');

		System.out.println("Hello!");
	}

	private static void play() {
		var currentPlayer = 0;
		while(continueGame()) {
			processPlayerInput(players.get(currentPlayer));
			if(++currentPlayer == players.size())
				currentPlayer = 0;
		}
	}

	private static void processPlayerInput(Player player) {
		while(true) {
			var input = in.nextLine().split(" ");
			var action = input[0];
			var args = Arrays.copyOfRange(input, 1, input.length);
			switch (action) {
				case "players":
					processActionPlayers(player);
					break;
				case "cards":
					processActionCards(player);
				case "use":
					processActionUseCard(player, args);
					break;
				case "discard":
					processActionDiscard(player, args);
					break;
				case "continue":
					return;
				default:
					System.out.println("[Unknown action]");
					break;
			}
		}
	}

	private static void processActionCards(Player player) {
		System.out.print("Players: \n\t");
		for(var card : player.getCards())
			System.out.print(" " + card);
		System.out.println();
	}

	private static void processActionPlayers(Player player) {
		System.out.print("Players: \n\t");
		for(var otherPlayers : players)
			System.out.print(otherPlayers.equals(player) ? "" : " " + otherPlayers.getName());
		System.out.println();
	}

	private static void processActionUseCard(Player player, String[] args) {
		if(args.length != 2)
			return;
		var card = args[0];
		var targetPlayer = args[1];

		player.useCard(card, targetPlayer);
	}

	private static void processActionDiscard(Player player, String[] args) {

	}

	private static boolean continueGame() {
		return true;
	}

	private static void initPlayers() {
		players = new ArrayList<>();

		for(var i = 0; i < PLAYER_COUNT; ++i) {
			var takeCards = normalCards.subList(0, PLAYER_CARD_COUNT);
			players.add(new Player("player_" + i, new ArrayList<>(takeCards)));

			takeCards.clear();
		}
	}

	private static void initQueenCards() {
		queenCards = new ArrayList<>();
		for(var i = 0; i < CARD_COUNT_KING; ++i)
			queenCards.add(new Card("queen_" + i));
	}
	private static void initNormalCards() {
		normalCards = new ArrayList<>();
		for(var i = 0; i < CARD_COUNT_KING; ++i)
			normalCards.add(new Card("king_" + i));
		for(var i = 0; i < CARD_COUNT_KNIGHT; ++i)
			normalCards.add(new Card("knight_" + i));
		for(var i = 0; i < CARD_COUNT_SLEEPING_POTION; ++i)
			normalCards.add(new Card("sleeping_potion_" + i));
		for(var i = 0; i < CARD_COUNT_WAND; ++i)
			normalCards.add(new Card("wand_" + i));
		for(var i = 0; i < CARD_COUNT_DRAGON; ++i)
			normalCards.add(new Card("dragon_" + i));

		for(var i = 0; i < CARD_COUNT_REGULAR; ++i)
			normalCards.add(new Card("heart_" + i));
		for(var i = 0; i < CARD_COUNT_REGULAR; ++i)
			normalCards.add(new Card("bell_" + i));
		for(var i = 0; i < CARD_COUNT_REGULAR; ++i)
			normalCards.add(new Card("acorn_" + i));
		for(var i = 0; i < CARD_COUNT_REGULAR; ++i)
			normalCards.add(new Card("leave_" + i));

		Collections.shuffle(normalCards, new Random(0));
	}
}