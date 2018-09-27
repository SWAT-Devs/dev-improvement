package dev.improvement

import groovy.transform.*

@CompileStatic
class EtchASketch {
	List<List<Boolean>> screen = new ArrayList<>()

	/**
	 * Print the screen to out, using 'X' for true and ' ' for false.
	 */
	void print(Appendable out){
		def ln = System.lineSeparator()
		screen.each {line -> 
			line.each {cell -> out.append(cell ? 'X' : ' ')}
			out.append(ln)
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
		int x = 0
		int y = 0
		screen = [[true]]
		for(int i; (i = reader.read()) > 0;){
			def c = i as char
			List<Boolean> line = null;
			switch(c){
				case '>':
					x++
					if(x < screen[y].size()){
						screen[y][x] = true
					}
					else{
						screen[y] << true
					}
					break;
				case 'v':
					y++
					if(y < screen.size()){
						line = screen[y]
					}
					else{ 
						line = []
						screen << line
					}
					fillLeft(line, x)
					break;
				case '<':
					x--
					if(x < 0){
						System.err.println "Too many '<'"
						x = 0
					}
					screen[y][x] = true
					break;
				case '^':
					y--
					if(y < 0){
						System.err.println "Too many '^'"
						y = 0
					}
					fillLeft(screen[y], x)
					break;
				default:
					System.err.println "Unrecognized symbol '$c'"
			}
		}
	}

	private static void fillLeft(List<Boolean> line, int x){
		while(x >= line.size()){
			line << false
		}
		line[x] = true
	}

	/**
     * Helper method to execute instructions stored in a string
	 */
	void run(String instructions){
		run(new StringReader(instructions))
	}
}
