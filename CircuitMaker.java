package edu.mccc.cos210.cmwar;

/**
 * CircuitMaker with AutoRouter. Advanced Printed Circuit Board Design. Pure Zen.
 *
 * @author Rohit Kumar
 *
 * @version 0.9
 */
public class CircuitMaker {

	/**
	 * The secret number. Blah, blah, blah.
	 */
	private int number = 42;

	/**
	 *
	 * Constructor. Create the original object
	 */
	public CircuitMaker() {
	}

	/**
	 * In the begining there was main. main is where it all
	 * begins. Blah, blah, blah.
	 *
	 * @param sa The command line arguments
	 */
	public static void main(String[] sa) {
		new CircuitMaker();
	}

	/**
	 * Inspector in charge of return the secret number. Blah, blah, blah.
	 * @return The secret number.
	 * @throws Exception The number is Invalid.
	 */
	public int getNumber() throws Exception {
		if (this.number != 42) {
			throw new Exception();
		}
		return this.number;
	}
}
