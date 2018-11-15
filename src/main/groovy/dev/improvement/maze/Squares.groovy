package dev.improvement.maze

@groovy.transform.CompileStatic
class Squares {
	void printSquares(int dimension, Appendable out) {
		def div = dimension / 2
		for(int y = 0; y < dimension; y++){
			if(y > 0){
				out << '\n'
			}
			for(int x = 0; x < dimension; x++) {
				if((y % 2 == 0 && (x % 2 == 0
													 ||(x >= y && x < dimension - y)
													 ||(y > div && x < y && x >= dimension - y)))
					 ||(x % 2 == 0 && ((y >= x && y < dimension - x)
														 ||(x > div && y < x && y >= dimension - x)))){
					out << '#'
				}
				else {
					out << ' '
				}
			}
		}
	}
}
