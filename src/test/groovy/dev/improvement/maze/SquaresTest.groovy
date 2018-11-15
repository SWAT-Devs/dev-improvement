package dev.improvement.maze

import static org.junit.Assert.*

import org.junit.*

class SquaresTest {
	@Test
	void test_3x3() {
		test 3, """###
# #
###"""
	}

	@Test
	void test_5x5() {
		test 5, """#####
#   #
# # #
#   #
#####"""
	}

	@Test
	void test_7x7() {
		test 7, """#######
#     #
# ### #
# # # #
# ### #
#     #
#######"""
	}

	@Test
	void test_9x9() {
		test 9, """#########
#       #
# ##### #
# #   # #
# # # # #
# #   # #
# ##### #
#       #
#########"""
	}

	@Test
	void test_11x11() {
		test 11, """###########
#         #
# ####### #
# #     # #
# # ### # #
# # # # # #
# # ### # #
# #     # #
# ####### #
#         #
###########"""
	}

	def test(int d, String expected) {
		def sb = new StringBuffer()
		def sq = new Squares()
		sq.printSquares(d, sb);
    println "$d x $d"
    println sb
		assertEquals("$d x $d", expected, sb.toString())
	}
}
