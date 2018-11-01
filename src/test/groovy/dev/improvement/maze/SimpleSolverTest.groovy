package dev.improvement.maze

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@groovy.transform.CompileStatic
class SimpleSolverTest {
		final IMaze maze
		SimpleSolverTest(String mazeString, String name){
				maze = new MazeReader().read(new StringReader(mazeString))
		}

		@Test
		void test_simple_solver() {
				def s = new SimpleSolver()
				def p = maze.start()
				def start = System.currentTimeMillis()
				s.solve(p)
				def dur = System.currentTimeMillis() - start
				println "Duration $dur ms. Moves taken ${p.movesTaken.size()}"
				maze.display(System.out, p)
				assertTrue("Solver has won", p.hasWon())
		}

		@Parameters(name = "{index}: {1}")
		static List<Object[]> mazes() {
				def mazes = [
						["""
###
#O#
# #
#X#""", "Simple"].toArray(),
						["""
#####
#O  #
### #
#X  #
#####""", "C Shape"].toArray(),
						["""
##########
#X       #
#        #
#        #
#       O#
##########""", "Big O"].toArray(),
						["""
#####
# #X#
#   #
# ###
#   #
# ###
#  O#
#####""", "Dead ends"].toArray(),
						["""
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
############################################################""", "Big Guy"].toArray(),
				]
				SimpleSolverTest.getResource('mazes.txt').eachLine{m ->
						mazes << [SimpleSolverTest.getResource(m).getText(), m].toArray()
				}
				return mazes
		}
}
