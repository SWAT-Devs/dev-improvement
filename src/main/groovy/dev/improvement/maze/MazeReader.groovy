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
		String line;
		Maze m = new Maze();
		while((line = reader.readLine()) != null) {
			if(!line.isEmpty()){
				List<Character> charList = Arrays.asList(line.toCharArray())
				m.addToMaze(charList)
				if(!m.isValid())
					print "not valid"
			}
		}

		return m
	}

	static class Maze implements IMaze{
		List<List<Character>> theMaze = new ArrayList<>();
		public Point startPoint;
		public Point target;

		public void addToMaze(List<Character> chars){
			theMaze.add(chars);
			int i = chars.indexOf('O' as char)
			if(i > -1)
				startPoint = new Point(i, theMaze.size()-1)
			i = chars.indexOf('X' as char)
			if(i > -1)
				target = new Point(i, theMaze.size()-1)
		}

		public boolean isValid(){
			return true;
		}

		public Point getStartLoc(){
			return startPoint
		}

		public char getCharAt(Point p){
			return theMaze[p.y][p.x]
		}

		@Override
		IPlayer start(){
			return new Player(this)
		}

		@Override
		void display(Appendable out){

		}

		@Override
		void display(Appendable out, IPlayer player){

		}
	}

	static class Point{
		public int x
		public int y
		Point(x, y){
			this.x = Math.max(0, x)
			this.y = Math.max(0, y)
		}

		@Override
		public String toString(){
			return "[$x, $y]"
		}

		@Override
		public boolean equals(Object p2){
			if(!(p2 instanceof Point))
				return false
			Point p = (Point) p2
			return p.x == this.x && p.y == this.y
		}
	}

	static class Player implements IPlayer{
		private List<Direction> moves = new ArrayList<>()
		private Maze maze;
		private Point currLoc

		public Player(Maze maze){
			this.maze = maze
			currLoc = maze.getStartLoc()
		}

		@Override
		Iterable<Direction> getMovesTaken(){
			return moves
		}

		@Override
		boolean canMove(Direction direction){
			Point nextPoint = getNext(direction);

			char c = maze.getCharAt(nextPoint)
			return c != '#'
		}

		@Override
		void move(Direction direction){
			if(canMove(direction)){
				currLoc = getNext(direction)
				moves.add(direction)
			}
		}

		private Point getNext(Direction direction){
			Point nextPoint;
			switch(direction) {
				case Direction.North:
					nextPoint = new Point(currLoc.x, currLoc.y - 1)
				break
				case Direction.South:
					nextPoint = new Point(currLoc.x, currLoc.y + 1)
				break
				case Direction.East:
					nextPoint = new Point(currLoc.x + 1, currLoc.y)
				break;
				case Direction.West:
					nextPoint = new Point(currLoc.x - 1, currLoc.y)
				break
			}
			return nextPoint
		}

		@Override
		boolean hasWon(){
			println currLoc
			println maze.target
			return currLoc.equals(maze.target)
		}
	}

}
