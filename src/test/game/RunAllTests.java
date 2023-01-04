package game;

import game.drawing.RunAllTestsDrawing;
import game.player.RunAllTestsPlayer;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		RunAllTestsPlayer.class,
		RunAllTestsDrawing.class
})
public class RunAllTests {
}
