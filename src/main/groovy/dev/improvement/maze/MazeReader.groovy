package dev.improvement.maze

/**
 * Reads in a maze from lines of text.
 * - lines represent the vertical axis of the maze with the top of the file 
 *   being north and the bottom being south.
 * - characters in the line represent the horizontal axis with left being West 
 *   and right being East.
 * - '#' represents walls
 * - 'O' represents the starting position
 * - 'X' represents the finish position
 * - anything else is seen as an open cell
 */
class MazeReader {
	/**
     * Create a maze from the input.
     */
	IMaze read(Reader reader){
		throw new IllegalStateException("not implemented")
	}
}
