package de.sample.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlingGameTest {
	private BowlingGame bowlingGame;

	@Before
	public void testInitialize() throws Exception {
		bowlingGame = new BowlingGame();
	}

	@Test
	public void testGutterGame() throws Exception {
		bowlingGame.bowl(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		assertEquals("The score for all gutter games must be zero", 0, bowlingGame.score());
	}

	@Test
	public void testPinOneForAllThrows() throws Exception {
		bowlingGame.bowl(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

		assertEquals(20, bowlingGame.score());
	}
	
	@Test
	public void testSpare() throws Exception {
		bowlingGame.bowl(6, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		assertEquals(16, bowlingGame.score());
	}
	@Test
	public void testStrike() throws Exception {
		bowlingGame.bowl(10, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);	

		assertEquals(26, bowlingGame.score());
	}

	@Test
	public void testStrikeAll() throws Exception {
		bowlingGame.bowl(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

		assertEquals(300, bowlingGame.score());
	}

	@Test
	public void testBowlingGameAcceptanceTest() throws Exception {
		bowlingGame.bowl(1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6);

		assertEquals(133, bowlingGame.score());
	}

}
