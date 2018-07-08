package de.sample.bowling;

public class BowlingGame {
	static final int MAXFRAMES = 10;
	static final int MAXPINS = 10;
	static final int MAXROLLS = (MAXFRAMES * 2 + 1);
	static final int INDEX_STANDARD_THROW_FIRSTBALL = 1;
	static final int INDEX_STANDARD_THROW_SECONDBALL = 2;
	static final int INDEX_BONUS_BALL = 3;
	private int[] rolls = new int[MAXROLLS];
	private Frame[] frames = new Frame[MAXFRAMES];

	public Frame[] getFrames() {
		return frames;
	}

	public void setFrames(Frame[] frames) {
		this.frames = frames;
	}

	private int currentRoll = 0;

	public void roll(int pins) {
		rolls[currentRoll++] = pins;
	}

	public int score() {
		int score = 0;
		int rollIndex = 0;

		for (int frameIndex = 0; frameIndex < MAXFRAMES; frameIndex++) {

			if (frames[frameIndex] == null) {
				frames[frameIndex] = new Frame();
			}

			if (isStrike(rollIndex)) {
				score += MAXPINS + rolls[rollIndex + 1] + rolls[rollIndex + 2];
				rollIndex++;
			} else if (isSpare(rollIndex)) {
				score += MAXPINS + rolls[rollIndex + 2];
				rollIndex += 2;
			} else {
				score += rolls[rollIndex] + rolls[rollIndex + 1];
				rollIndex += 2;
			}
			frames[frameIndex].setScore(score);
		}

		return score;
	}

	private boolean isStrike(int rollIndex) {
		return rolls[rollIndex] == MAXPINS;
	}

	private boolean isSpare(int rollIndex) {
		return rolls[rollIndex] + rolls[rollIndex + 1] == MAXPINS;
	}

	public void bowl(int... rolls) {
		for (int pins : rolls) {
			roll(pins);
		}
	}

}
