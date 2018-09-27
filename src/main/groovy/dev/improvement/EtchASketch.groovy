package dev.improvement

import groovy.transform.*

@CompileStatic
class EtchASketch {
	List<List<Boolean>> screen = new ArrayList<>()

	/**
	 * Print the screen to out, using 'X' for true and ' ' for false.
	 */
	void print(Appendable out){
		//TODO Implement here
	}

	/**
	 * Helper method to print the screen to the standard output.
	 */
	void print() {
		print(System.out)
	}

	/**
	 * Clear the screen field, read instructions from the reader, and execute them by drawing
	 * a true in the 2D array for each move of the cursor.
     * Instructions:
     *   > move right
     *   < move left
     *   ^ move up
     *   v move down
     * See test case for examples.
	 */
	void run(Reader reader){
		//TODO Implement here
	}

	/**
     * Helper method to execute instructions stored in a string
	 */
	void run(String instructions){
		run(new StringReader(instructions))
	}
}
