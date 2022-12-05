import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SleepingQueens {
	private static final int CARD_COUNT_QUEEN = 12;
	private static final int CARD_COUNT_KING = 8;
	private static final int CARD_COUNT_KNIGHT = 4;
	private static final int CARD_COUNT_SLEEPING_POTION = 4;
	private static final int CARD_COUNT_WAND = 3;
	private static final int CARD_COUNT_DRAGON = 3;
	private static final int CARD_COUNT_REGULAR = 10;
	private static final int PLAYER_COUNT = 4;
	private static final int PLAYER_CARD_COUNT = 4;

	static ArrayList<Card> normalCards;
	static ArrayList<Card> queenCards;
	static ArrayList<Player> players;

	public static void main(String[] args) {
		initNormalCards();
		initQueenCards();
		initPlayers();

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