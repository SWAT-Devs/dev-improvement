package dev.improvement.maze

class SimpleSolver implements ISolver {
	
		List<Direction> getPossibleMoves(IPlayer player, List<Direction> usedDirections) {
			Direction[] dirlist = [Direction.West, Direction.East, Direction.North, Direction.South]
			/* Interesting note:
			 * the Direction Enum lists North East South West, which in an upper right corner creates an infinite loop with this algorithm
			 * But if we change the list to something like West East North South, we eliminate that problem (And probably create a new one)
			 * A real fix for this problem would track which squares were visited previously to stop infinite loops
			 * don't worry i have some crazy ideas on how to implement that idea
			 */
			List<Direction> l = new ArrayList<Direction>()
			for(Direction dir : dirlist) {
				if(!usedDirections.contains(dir) && player.canMove(dir)) {
					l.add(dir)
				}
			}
			return l
		}
		
		@Override
		void solve(IPlayer player) {
			List<List<Direction>> previousMoves = new ArrayList<ArrayList<Direction>>()
			previousMoves.add(new ArrayList<Direction>())
			int index = 0
			List<Direction> possibleMoves = new ArrayList<Direction>()
			Map<Direction, Direction> map = new HashMap<Direction, Direction>()
			map.put(Direction.North, Direction.South)
			map.put(Direction.South, Direction.North)
			map.put(Direction.West, Direction.East)
			map.put(Direction.East, Direction.West)
			
			
			while(!player.hasWon()) {
				if(previousMoves.size() == 0) {
					//If we've managed to hit a point where we're back at square 1 with nowhere else to try, there's probably no exit
					println "Failure"
					return
				}
				
				possibleMoves = getPossibleMoves(player, previousMoves[index])
				if(possibleMoves.size == 0) {
					//If there are no possible moves
					player.move(previousMoves[index][0]) //Move back a step - the first entry in the current move is the direction we came from
					previousMoves.remove(index) //drop this latest move from the list so we effectively remove the move from our path
					index--
				} else {
					//Otherwise if there are possible moves
					Direction toMove = possibleMoves[0] //just take the first viable direction (Priorty = W E N S)
					player.move(toMove) //Move there
					previousMoves[index].add(toMove) //Add the direction to the list so we know we took this direction
					List<Direction> toAdd = new ArrayList<Direction>() //Make a new move
					toAdd.add(map.get(toMove)) //Add the direction we came from so we can't just go backwards when we can go forewards
					previousMoves.add(toAdd) //Add the move to the top of the list - now we'll make a new move based off of it
					index++
				}
			}
		}
}
