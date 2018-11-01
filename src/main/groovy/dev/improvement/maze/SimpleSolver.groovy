package dev.improvement.maze

class SimpleSolver implements ISolver {
	Direction lastDirTaken;

	@Override
	void solve(IPlayer player) {
		while(!player.hasWon()){
			Direction dir = nextMoveRandom();
			if(player.canMove(dir) 
				&& (lastDirTaken == null || lastDirTaken != getOppositeDir(dir))){
				player.move(dir)
				lastDirTaken = dir
			}
			else{
				if(lastDirTaken == Direction.North){
					player.move(Direction.South)
					lastDirTaken = Direction.South
				}
				else if(lastDirTaken == Direction.West){
					player.move(Direction.East)
					lastDirTaken = Direction.East
				}
				else if(lastDirTaken == Direction.South){
					player.move(Direction.North)
					lastDirTaken = Direction.North
				}
				else if(lastDirTaken == Direction.East){
					player.move(Direction.West)
					lastDirTaken = Direction.West
				}
			}
		}
	}

	Direction nextMoveRandom(){
		def r = (int) (Math.random() * 4)
		return Direction.values()[r]
	}

	Direction getOppositeDir(Direction dir){
		switch(dir){
			case Direction.South:
				return Direction.North
			case Direction.West:
				return Direction.East
			case Direction.North:
				return Direction.South
			case Direction.East:
				return Direction.West
		}
	}
}
