package dev.improvement.maze
import static dev.improvement.maze.Direction.*

@groovy.transform.CompileStatic
class SimpleSolver implements ISolver {
		@Override
		void solve(IPlayer player) {
				Direction last = North;
				while(!player.hasWon()){
						Direction left = LEFT[last]
						Direction current
						if(player.canMove(left)){
								//If you can turn left
								current = left
						}
						else if(player.canMove(last)){
								//If you can go straight
								current = last
						}
						else{
								Direction right = RIGHT[last]
								//If you can turn right, else go back.
								current = player.canMove(right) ? right : OPPOSITE[last]
						}
						player.move current
						last = current
				}
		}

		private static final Map<Direction, Direction> LEFT = new EnumMap<>(Direction)
		private static final Map<Direction, Direction> RIGHT = new EnumMap<>(Direction)
		private static final Map<Direction, Direction> OPPOSITE = new EnumMap<>(Direction)
		static {
				LEFT[North] = West
				LEFT[West] = South
				LEFT[South] = East
				LEFT[East] = North
				
				RIGHT[North] = East
				RIGHT[East] = South
				RIGHT[South] = West
				RIGHT[West] = North
				
				OPPOSITE[North] = South
				OPPOSITE[South] = North
				OPPOSITE[East] = West
				OPPOSITE[West] = East
		}
}
