package de.sample.bowling;

public class Frame {
	private int frameNr;
	private int score;
	private String frameBalls;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getFrameBalls() {
		return frameBalls;
	}

	public void setFrameBalls(String frameballs) {
		this.frameBalls = frameballs;
	}

	public int getFrameNr() {
		return frameNr;
	}

	public void setFrameNr(int frameNr) {
		this.frameNr = frameNr;
	}

	@Override
	public String toString() {
		return "Frame [score=" + score + ", ball1=" + frameBalls + ", frameNr=" + frameNr + "]";
	}
	
	
}
