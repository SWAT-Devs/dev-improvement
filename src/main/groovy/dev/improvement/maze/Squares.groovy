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
//   012345678
// 0 #########
// 1 #       #
// 2 # ##### #
// 3 # #   # #
// 4 # # # # #
// 5 # #   # #
// 6 # ##### #
// 7 #       #
// 8 #########
	void printSquares(int dimension, Appendable out) {
		//TODO do stuff
		int mid = (dimension / 2 ) + 1

		for(int i = 0; i < dimension; i++) {
			int y = i % 2
			for(int j = 0; j < dimension; j++){
				int x = j % 2
				if(x == 0 && y == 0){
					out.append("#")
				}
				else if (i == 0 || j == 0){
					out.append("#")
				}
				else if(j >= i && j < dimension-i && y == 0) {
					out.append("#")
				}
				else if(i >= j && i < dimension-j && x == 0) {
					out.append("#")
				}
				else if(j <= i && j >= dimension-i && y == 0) {
					out.append("#")
				}
				else if(i <= j && i >= dimension-j && x == 0) {
					out.append("#")
				}
				else{
					out.append(" ")
				}
			}
			if(i != dimension-1)
				out.append("\n")

			// if(i == 0 || i == dimension-1) {
			// 	out.append("#"*dimension)
			// }
			// else {
			// 	out.append("#")
			// 	if(i % 2 == 0) {
			// 		out.append(" ")
			// 		out.append("#")
			// 		out.append(" ")
			// 	} else {	
			// 		out.append(" "*(dimension-2))
			// 	}
			// 	out.append("#")
			// }
			// if(i < dimension-1) {
			// 	out.append("\n")
			// }
		}
	}
}
