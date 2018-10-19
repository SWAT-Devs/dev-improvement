package dev.improvement.maze

/**
 * Represents a player trying to find a route through the maze.
 */
interface IPlayer {
	/**
     * All the moves that the player has made to this point.
     */
	Iterable<Direction> getMovesTaken()

	/**
     * Can the player move in the given direction from their current location.
     */
	boolean canMove(Direction direction)

	/**
     * Move the player in the given direction.
     */
	void move(Direction direction)

	/**
     * Is the player standing on the finish cell.
     */
	boolean hasWon()
}
