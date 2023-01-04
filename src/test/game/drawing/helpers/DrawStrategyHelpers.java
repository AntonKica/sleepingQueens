package game.drawing.helpers;

import game.card.Card;
import game.card.CardType;

import java.util.ArrayList;
import java.util.List;

public class DrawStrategyHelpers {
	public static List<Card> test_create_card_deck() {
		var res = new ArrayList<Card>();
		for(var i = 0; i < 8; ++i)
			res.add(new Card(CardType.KING, i));
		for(var i = 0; i < 4; ++i)
			res.add(new Card(CardType.KNIGHT, i));
		for(var i = 0; i < 4; ++i)
			res.add(new Card(CardType.SLEEPING_POTION, i));
		for(var i = 0; i < 3; ++i)
			res.add(new Card(CardType.DRAGON, i));
		for(var i = 0; i < 3; ++i)
			res.add(new Card(CardType.MAGIC_WAND, i));
		for(var i = 0; i < 10; ++i) {
			res.add(new Card(CardType.NUMBER, i + 1));
			res.add(new Card(CardType.NUMBER, i + 1));
			res.add(new Card(CardType.NUMBER, i + 1));
			res.add(new Card(CardType.NUMBER, i + 1));
		}

		return res;
	}
}
