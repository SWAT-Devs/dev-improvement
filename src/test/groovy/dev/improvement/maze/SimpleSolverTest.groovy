package dev.improvement.maze

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
class SimpleSolverTest {
		final IMaze maze
		SimpleSolverTest(String mazeString){
				maze = new MazeReader().read(new StringReader(mazeString))
		}

		@Test
		void test_simple_solver() {
				def s = new SimpleSolver()
				def p = maze.start()
				def start = System.currentTimeMillis()
				s.solve(p)
				def dur = System.currentTimeMillis() - start
				println "Duration $dur ms."
				maze.display(System.out, p)
				assertTrue("Solver has won", p.hasWon())
		}

		@Parameters
		static List<String> mazes() {
				[
						"""
###
#O#
# #
#X#""",
						"""
#####
#O  #
### #
#X  #
#####""",
						"""
##########
#X       #
#        #
#        #
#       O#
##########""",
						"""
#####
# #X#
#   #
# ###
#   #
# ###
#  O#
#####""",
						"""
############################X###############################
#                   ########                               #
################### ################ ######## ######### ####
# ################# ################ #################### ##
#                                                         ##
# ####################################################### ##
#                                                  #      ##
# ################################################ #########
# ## ####### ########### ######## ######                  ##
#                                  ############### ###### ##
# ################################################ #       #
################               ################### #########
#           ############## ### #                           #
# ############             ### # ###### ####################
#            ################# ######## ##########  #      #
############                                       ####### #
#            ################# #######################     #
#### #########################                         #####
# ##                        ################################
# #########################  #                             #
# ############              ## ########################### #
# ############ ############### #                         # #
#                            # # ####################### # #
############## ############### # ####################  # # #
#                            # #                       # # #
############################ # ######################### # #
#                            #                           # #
############################ ############################# #
#                           O                              #
############################################################"""
				]
		}
}
