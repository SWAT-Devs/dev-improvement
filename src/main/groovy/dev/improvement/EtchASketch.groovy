package dev.improvement

import groovy.transform.*

@CompileStatic
class EtchASketch {
	List<List<Boolean>> screen = new ArrayList<>()
	Point currLoc = new Point()

	/**
	 * Print the screen to out, using 'X' for true and ' ' for false.
	 */
	void print(Appendable out){
		for(int i = 0; i < screen.size(); i++){
			def row = screen[i]
			for(cell in row){
				cell ? out.append('X') : out.append(' ')
			}
			if(i < screen.size() - 1)
				out.append(System.getProperty("line.separator"))
		}
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
		screen = new ArrayList<>()
		screen.add(new ArrayList<>())
		screen[0][0] = true
		
		char input
		while((input = reader.read()) > -1){
			Direction d = Direction.findByChar(input)
			currLoc.moveBy(d.movement)
		}
	}

	enum Direction{
		RIGHT('>' as char, new Point(1, 0)),
		LEFT('<' as char, new Point(-1, 0)),
		UP('^' as char, new Point(0, -1)),
		DOWN('v' as char, new Point(0, 1))

		char c
		Point movement
		Direction(char c, Point movement){
			this.c = c;
			this.movement = movement
		}

		public static Direction findByChar(char theChar){
			return Direction.values().find({
				it.c == theChar
			})
		}
	}

	/**
     * Helper method to execute instructions stored in a string
	 */
	void run(String instructions){
		run(new StringReader(instructions))
	}

	static class Point{
		int x = 0
		int y = 0

		Point(){}

		Point(int x, int y){
			this.x = x
			this.y = y
		}

		public void moveBy(Point p){
			this.x += p.x
			this.y += p.y
		}
	}
}
