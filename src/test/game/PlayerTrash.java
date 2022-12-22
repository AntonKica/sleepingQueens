package game;

public class PlayerTrash {
	/*
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
		var hand = new PlayerHand(drawingAndTrashPile, List.of(handCard));

		var sleepingQueens = new SleepingQueens(List.of(new Queen(10)));

		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, new AwokenQueens(0));
		awokenQueens.get(0).addQueen(new Queen(10));

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
				new SleepingQueenPosition(1),
				false,
				"Players king picked non-existent awoken queen from player 1 at 0");
	}
	@Test
	public void awokenQueen() {
		assertPlayAwokenQueen(
				new Card(CardType.KNIGHT, 0),
				new Card(CardType.DRAGON, 0),
				false,
				"Players knight did wrong against dragon");
		assertPlayAwokenQueen(
				new Card(CardType.KNIGHT, 0),
				new Card(CardType.MAGIC_WAND, 0),
				true,
				"Players knight did wrong against card");
		assertPlayAwokenQueen(
				new Card(CardType.SLEEPING_POTION, 0),
				new Card(CardType.MAGIC_WAND, 0),
				false,
				"Players sleeping potion did wrong against magic wand");
		assertPlayAwokenQueen(
				new Card(CardType.SLEEPING_POTION, 0),
				new Card(CardType.DRAGON, 0),
				true,
				"Players sleeping potion wrong did wrong against card");
	}

	private void assertPlayAwokenQueen(Card aggressorCard, Card defenderCard, boolean shouldSucceed, String message) {
		var drawCards = List.of(new Card(CardType.NUMBER, -1));
		when(drawingAndTrashPile.draw(1)).thenReturn(drawCards);

		var aggressorHand = new PlayerHand(drawingAndTrashPile, List.of(aggressorCard));
		var defenderHand = new PlayerHand(drawingAndTrashPile, List.of(defenderCard));
		var hands = new HashMap<Integer, PlayerHand>();
		hands.put(0, aggressorHand);
		hands.put(1, defenderHand);

		var aggressorAwokenQueens = new AwokenQueens(0);
		var defenderAwokenQueens = new AwokenQueens(1);
		var defenderAwokenQueen = new Queen(10);
		defenderAwokenQueens.addQueen(defenderAwokenQueen);

		var awokenQueens = new HashMap<Integer, AwokenQueens>();
		awokenQueens.put(0, aggressorAwokenQueens);
		awokenQueens.put(1, defenderAwokenQueens);

		var aggressorPlayer = new Player(
				aggressorHand, hands,
				new SleepingQueens(new ArrayList<>()),
				aggressorAwokenQueens, awokenQueens);
		var defenderPlayer = new Player(
				defenderHand, hands,
				new SleepingQueens(new ArrayList<>()),
				defenderAwokenQueens, awokenQueens);

		var aggressorState = aggressorPlayer.play(List.of(new HandPosition(0, 0), new AwokenQueenPosition(1, 0)));
		var defenderState = defenderPlayer.getPlayerState();

		var aggressorCards = aggressorState.getCards().values().stream().toList();
		var defenderCards = defenderState.getCards().values().stream().toList();
		var aggressorQueens = aggressorState.getAwokenQueens().values().stream().toList();
		var defenderQueens = defenderState.getAwokenQueens().values().stream().toList();

		if(shouldSucceed) {
			Assert.assertTrue(message,
					(aggressorCards.equals(drawCards) && !defenderCards.equals(drawCards)) &&
							(aggressorQueens.size() == 1 && aggressorQueens.contains(defenderAwokenQueen)) &&
							(defenderQueens.isEmpty()));
		} else {
			Assert.assertTrue(message,
					(aggressorCards.equals(drawCards) && defenderCards.equals(drawCards)) &&
							(aggressorQueens.isEmpty()) &&
							(defenderQueens.size() == 1 && defenderQueens.contains(defenderAwokenQueen)));
		}
	}

	 */
}
