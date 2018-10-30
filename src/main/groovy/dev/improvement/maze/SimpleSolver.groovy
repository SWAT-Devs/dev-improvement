package dev.improvement.maze
import static dev.improvement.maze.Direction.*

@groovy.transform.CompileStatic
class SimpleSolver implements ISolver {
		@Override
		void solve(IPlayer player) {
				Direction last = Direction.North;
				while(!player.hasWon()){
						def left = getLeft(last)
						Direction current
						if(player.canMove(left)){
								current = left
						}
						else if(player.canMove(last)){
								current = last
						}
						else{
								def o = getOpposite(last)
								for(def i = 0; i< 4 && (left == o || !player.canMove(left)); i++){
										left = getLeft(left)
								}
								current = player.canMove(left) ? left : o;
						}
						player.move current
						last = current
				}
		}

		private static Direction getLeft(Direction d){
				switch(d){
						case North: return West
						case West: return South
						case South: return East
						case East: return North
						default: throw new IllegalStateException()
				}
		}

		private static Direction getOpposite(Direction d){
				switch(d) {
						case North: return South
						case South: return North
						case East: return West
						case West: return East
						default: throw new IllegalStateException()
				}
		}
}
