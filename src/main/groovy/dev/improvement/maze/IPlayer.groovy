package dev.improvement.maze

interface IPlayer {
	Iterable<Direction> getMovesTaken()
	boolean canMove(Direction direction)
	void move(Direction direction)
	boolean hasWon()
}
