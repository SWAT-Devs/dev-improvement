package dev.improvement.maze

import static dev.improvement.maze.Direction.*

@groovy.transform.CompileStatic
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
	public static final char WALL = '#'
	public static final char START = 'O'
	public static final char FINISH = 'X'

	/**
     * Create a maze from the input.
     */
	IMaze read(Reader reader){
		def m = new Maze()
		reader.eachLine {line, y ->
			boolean[] row = new boolean[line.length()]
			for(int x = 0; x<line.length(); x++){
				row[x] = line[x] != WALL
				if(line[x] == START){
					m.startX = x
					m.startY = y-1
				}
				else if(line[x] == FINISH) {
					m.finishX = x
					m.finishY = y-1
				}	
			}
			m.board << row
		}
		return m
	}

	private static class Maze implements IMaze {
		List<boolean[]> board = []
		int startX = -1
		int startY = -1
		int finishX = -1
		int finishY = -1

		@Override
		IPlayer start() {
			new Player(this)
		}

		@Override
		void display(Appendable out) {
			display(out, null)
		}

		@Override
		void display(Appendable out, IPlayer player){
			def render = new char[board.size()][]
			for(int y =0; y < board.size(); y++){
				render[y] = new char[board[y].length]
				for(int x = 0; x < board[y].length; x++){
					render[y][x] = board[y][x] ? ((char)' ') : WALL
				}
			}
			if(player != null){
				def temp = new Player(this)
				player.movesTaken.each {
					temp.move it
					render[temp.y][temp.x] = render[temp.y][temp.x] >= (char)'1' ? (char)((int)render[temp.y][temp.x] + 1) : (char)'1'
				}
			}
			render[startY][startX] = START
			render[finishY][finishX] = FINISH
			def ln = System.lineSeparator()
			for(int y =0; y< render.length; y++){
				for(int x = 0; x<render[y].length; x++){
					out.append(render[y][x])
				}
				out.append(ln)
			}
		}
	}

	private static class Player implements IPlayer {
		final Maze maze
		int x
		int y
		final List<Direction> moves = []

		Player(Maze maze){
			this.maze = maze
			x = maze.startX
			y = maze.startY
		}
		
		@Override
		Iterable<Direction> getMovesTaken(){
			new ArrayList<>(moves)
		}

		boolean testAndSet(Direction dir, boolean shouldSet){
			int newX = x
			int newY = y
			switch(dir){
				case North:
					newY--
					break
				case South:
					newY++
					break
				case East:
					newX++
					break
				case West:
					newX--
					break
				default:
					throw new IllegalArgumentException("Unknown direction $dir")
			}
			boolean valid = newX >= 0 && newY >= 0 && newY < maze.board.size() && newX < maze.board[newY].length && maze.board[newY][newX]
			if(valid && shouldSet){
				x = newX
				y = newY
			}
			return valid
		}
		
		@Override
		boolean canMove(Direction direction){
			return testAndSet(direction, false)
		}
		
		@Override
		void move(Direction direction){
			if(!testAndSet(direction, true)){
				throw new IllegalArgumentException("Invalid move direction $direction")
			}
			moves << direction
		}
		
		@Override
		boolean hasWon(){
			return x == maze.finishX && y == maze.finishY	
		}
	}
}
