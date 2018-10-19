package dev.improvement.maze

import static org.junit.Assert.*
import static dev.improvement.maze.Direction.*

import org.junit.Test

@groovy.transform.CompileStatic
class MazeReaderTest {
	private final MazeReader mr = new MazeReader()
	@Test
	void single_column_maze() {
		def player = play("""
###
#O#
# #
#X#""")
		assertValidMoves(player, South)
		assertFalse("haven't won yet", player.hasWon())
		player.move(South)
		assertValidMoves(player, South, North)
		assertFalse("haven't won yet", player.hasWon())
		player.move(South)
		assertTrue("has won", player.hasWon())
		assertEquals("moves taken", [South, South], player.movesTaken)
	}

	@Test
	void ring_maze() {
		def maze = mr.read(new StringReader("""
#####
#O  #
# # #
#  X#
#####
"""))
		def p1 = maze.start()
		assertValidMoves(p1, South, East)
		p1.move East
		assertValidMoves(p1, East, West)
		p1.move East
		assertValidMoves(p1, South, West)
		p1.move South
		assertValidMoves(p1, South, North)
		p1.move South
		assertTrue(p1.hasWon())

		maze.display(System.out, p1)

		def p2 = maze.start()
		p2.move South
		assertValidMoves(p2, North, South)
		p2.move South
		assertValidMoves(p2, North, East)
		p2.move East
		assertValidMoves(p2, West, East)
		p2.move East
		p2.move West

		maze.display(System.out, p2)
	}

	IPlayer play(String str) {
		def maze = mr.read(new StringReader(str))
		maze.display(System.out)
		maze.start()
	}

	void assertValidMoves(IPlayer maze, Direction... validMoves){
		def good = EnumSet.copyOf(Arrays.asList(validMoves))
		for(def d in Direction.values()) {
			assertEquals("Can move $d", good.contains(d), maze.canMove(d))
		}
	}
}

