package game.player;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		PlayerNumberedCardsTests.class,
		PlayerSleepingQueenTests.class,
		PlayerAwokenQueenKnightTests.class,
		PlayerAwokenQueenSleepingPotionTests.class
})
public class RunAllTestsPlayer {
}
