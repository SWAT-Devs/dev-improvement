package dev.improvement.maze

import dev.improvement.ICommand

class Squares implements ICommand{
	@Override
	void run(List<String> args) {
		for(def arg in args) {
			printSquares(arg.toInteger(), System.out)
			println()
		}
	}
	
	void printSquares(int dimension, Appendable out) {
		//TODO do stuff
		for(int i = 0; i < dimension; i++) {
			if(i == 0 || i == dimension-1) {
				out.append("#"*dimension)
			}
			else {
				out.append("#")
				if(i % 2 == 0) {
					out.append(" ")
					out.append("#")
					out.append(" ")
				} else {	
					out.append(" "*(dimension-2))
				}
				out.append("#")
			}
			if(i < dimension-1) {
				out.append("\n")
			}
		}
	}
}
