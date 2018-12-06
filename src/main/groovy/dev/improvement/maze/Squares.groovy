package dev.improvement.maze

import dev.improvement.ICommand

@groovy.transform.CompileStatic
class Squares implements ICommand{
	@Override
	void run(List<String> args) {
		for(def arg in args) {
			printSquares(arg.toInteger(), System.out)
			println()
		}
	}
	
	void printSquares(int dimension, Appendable out) {
		if(dimension % 2 ==0){
			dimension = dimension - 1
		}
		for(int y = 0; y < dimension; y++){
			if(y > 0){
				out << '\n'
			}
			for(int x = 0; x < dimension; x++) {
				if((y % 2 == 0 && (x % 2 == 0
													 ||(x >= y && x < dimension - y)
													 ||(x < y && x >= dimension - y)))
					 ||(x % 2 == 0 && ((y >= x && y < dimension - x)
														 ||(y < x && y >= dimension - x)))){
					out << '#'
				}
				else {
					out << ' '
				}
			}
		}
	}
}
