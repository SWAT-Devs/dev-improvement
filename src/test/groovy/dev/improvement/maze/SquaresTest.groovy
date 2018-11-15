package dev.improvement.maze

import static org.junit.Assert.*

import org.junit.*

class SquaresTest {
	@Test
	void test_3x3() {
		def sb = new StringBuffer()
		printSquares(3, sb);
		assertEquals("""###
# #
###""", sb.toString())
	}

	@Test
	void test_5x5() {
		def sb = new StringBuffer()
		printSquares(5, sb);
		assertEquals("""#####
#   #
# # #
#   #
#####""", sb.toString())
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
