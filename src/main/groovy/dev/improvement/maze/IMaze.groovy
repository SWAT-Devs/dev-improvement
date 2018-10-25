package dev.improvement.maze

/**
 * An interface representing a 2D maze.
 */
interface IMaze {
	/**
     * Returns a player located at the start of the maze.
     */
	IPlayer start()

	/**
     * Prints the maze to the Appendable output using the same rules as below.
     */
	void display(Appendable out)

	/**
     * Prints the maze to the Appendable output using the character '#' to represent walls,
     * 'O' to represent start, and 'X' to represent finish.  If a player is specified then
     * a number should be used to represent each cell the player has passed over, starting 
     * at '1' and incrementing each time the player passes over the cell.  All empty cells 
     * should contain ' ' spaces.
     */
	void display(Appendable out, IPlayer player)
}
