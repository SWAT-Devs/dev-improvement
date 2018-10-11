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
		for(row in screen){
			for(cell in row){
				cell ? out.append('X') : out.append(' ')
			}
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
		currLoc = new Point();
		screen.add(new ArrayList<>())
		screen[0][0] = true
		
		char input
		while((input = reader.read()) > -1){
			Direction d = Direction.findByChar(input)
			currLoc.moveBy(d.movement)
			updateScreen()
		}
	}
	
	void updateScreen(){
		List<Boolean> row;
		if(currLoc.row >= screen.size()){
			row = new ArrayList<>();// newRow(screen[0].size())
			screen.add(row)
		}
		else
			row = screen[currLoc.row]
		
		while(row.size() <= currLoc.col)
			row.add(false)
		
		screen[currLoc.row][currLoc.col] = true;
	}
	
	List<Boolean> newRow(int size){
		List<Boolean> r = new ArrayList<>();
		for (int i = 0; i < size; i++){
			r.add(false);
		}
		return r;
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
		int col = 0
		int row = 0

		Point(){}

		Point(int col, int row){
			this.col = col
			this.row = row
		}

		public void moveBy(Point p){
			this.col += p.col
			this.row += p.row
		}
	}
}
