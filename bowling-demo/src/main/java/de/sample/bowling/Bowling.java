package de.sample.bowling;

import static de.sample.bowling.BowlingGame.INDEX_BONUS_BALL;
import static de.sample.bowling.BowlingGame.INDEX_STANDARD_THROW_FIRSTBALL;
import static de.sample.bowling.BowlingGame.INDEX_STANDARD_THROW_SECONDBALL;
import static de.sample.bowling.BowlingGame.MAXFRAMES;
import static de.sample.bowling.BowlingGame.MAXPINS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bowling {
	public static void main(String args[]) throws IOException {
		BowlingGame game = new BowlingGame();
		Frame frame[] = new Frame[MAXFRAMES];
		List<Integer> pinnedDownList = new ArrayList<Integer>();
		List<Integer> rolledPinList = new ArrayList<Integer>();

		for (int frameIndex = 0; frameIndex < MAXFRAMES; frameIndex++) {

			System.out.println("Frame " + (frameIndex + 1));
			frame[frameIndex] = new Frame();
			frame[frameIndex].setFrameNr(frameIndex);

			StringBuilder frameBuilder = new StringBuilder();
			pinnedDownList.clear();

			pinnedDownList.add(getScoreAsInput(frameIndex, INDEX_STANDARD_THROW_FIRSTBALL, pinnedDownList));
			rolledPinList.add(pinnedDownList.get(0));

			frameBuilder.append(pinnedDownList.get(0) == MAXPINS ? "X" : String.valueOf(pinnedDownList.get(0)));

			if (isSecondThrowAllowed(frameIndex, pinnedDownList)) {
				pinnedDownList.add(getScoreAsInput(frameIndex, INDEX_STANDARD_THROW_SECONDBALL, pinnedDownList));
				frameBuilder.append("|");
				rolledPinList.add(pinnedDownList.get(1));
				frameBuilder
						.append((frameIndex == MAXFRAMES - 1
								&& pinnedDownList.get(0) == MAXPINS)
										? "X"
										: (((frameIndex == MAXFRAMES - 1 && pinnedDownList.get(0) + pinnedDownList.get(1) == MAXPINS)
												| (pinnedDownList.get(0) + pinnedDownList.get(1) == MAXPINS)) ? "/"
														: String.valueOf(pinnedDownList.get(1))));
			} else {
				frameBuilder.append("-");
			}

			if (isSpecialBonusThrowAllowed(frameIndex, pinnedDownList)) {
				pinnedDownList.add(getScoreAsInput(frameIndex, INDEX_BONUS_BALL, pinnedDownList));
				frameBuilder.append("|");
				rolledPinList.add(pinnedDownList.get(2));
				frameBuilder.append(
						(frameIndex == MAXFRAMES - 1 && pinnedDownList.get(2) == MAXPINS) ? "X" : String.valueOf(pinnedDownList.get(2)));
			}
			frame[frameIndex].setFrameBalls(frameBuilder.toString());
		}
		game.setFrames(frame);
		int[] pins = rolledPinList.stream().mapToInt(t -> t).toArray();
		game.bowl(pins);
		game.score();
		printFramesAndScores(game.getFrames());

	}

	private static void printFramesAndScores(Frame[] frame) {
		System.out.println("\n ORACLE BOWLING WORLD :: HAVE LOTS OF FUN  (STRIKE --> X : SPARE --> / )");
		System.out.println(
				"*****************************************************************************************************************************************************************************************");
		System.out.print("Frame" + "\t");
		for (Frame frames : frame) {
			System.out.print("\t" + (frames.getFrameNr() + 1) + "\t");
		}
		System.out.println("\n"
				+ "************************************************************************************************************************************************************************************");
		System.out.print("Pins" + "\t");
		for (Frame frames : frame) {
			System.out.print("\t" + frames.getFrameBalls()+ "\t");
		}
		System.out.println("\n"
				+ "*************************************************************************************************************************************************************************************");
		System.out.print("Scores" + "\t");
		for (Frame frames : frame) {
			System.out.print("\t" + frames.getScore() + "\t");
		}

		System.out.println("\n"
				+ "************************************************************************************************************************************************************************************");
	}

	@SuppressWarnings("resource")
	static int getScoreAsInput(int frameIndex, int ballIndex, List<Integer> rollList) {
		boolean inputCheck = true;
		int input = 0;
		Scanner scan = new Scanner(System.in);
		try {

			do {
				inputCheck = true;
				System.out.println("Enter the value for Ball --> " + ballIndex);
				input = scan.nextInt();
				if ((ballIndex == INDEX_STANDARD_THROW_FIRSTBALL || ballIndex == INDEX_BONUS_BALL)
						&& (input < 0 || input > MAXPINS)) {
					System.out.println("Enter Number between 0 and 10");
					inputCheck = false;
				} else if (ballIndex == INDEX_STANDARD_THROW_SECONDBALL && rollList != null && rollList.get(0) != null
						&& rollList.get(0) < 10 && rollList.get(0) + input > MAXPINS) {
					System.out.println("Input must be less than or equals to " + (MAXPINS - rollList.get(0)));
					inputCheck = false;
				}

			} while (inputCheck == false);

		} catch (InputMismatchException ex) {
			System.out.println("Enter Valid Input .. Input should be a number");
			getScoreAsInput(frameIndex, ballIndex, rollList);
		}
		return input;
	}

	static boolean isSpecialBonusThrowAllowed(int frameIndex, List<Integer> rollList) {
		if (rollList != null && frameIndex == MAXFRAMES - 1) {
			return (rollList.get(0) == MAXFRAMES)
					|| (rollList.size() == 2 && rollList.get(0) + rollList.get(1) == MAXFRAMES);
		}
		return false;
	}

	static boolean isSecondThrowAllowed(int frameIndex, List<Integer> rollList) {
		if (rollList != null && rollList.size() == 1) {
			return (rollList.get(0) < MAXFRAMES) || (frameIndex == MAXFRAMES - 1 && rollList.get(0) == MAXFRAMES);
		}
		return false;
	}
}
