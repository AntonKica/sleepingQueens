package game;

import game.queens.PlayerAwokenQueens;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.HexFormat;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		PlayerNumberedCardsTests.class,
		PlayerSleepingQueenTests.class,
		PlayerAwokenQueenKnightTests.class
})
public class AllTests {
	public void run_all_tests() {

	}
}
