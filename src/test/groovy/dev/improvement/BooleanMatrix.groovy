package dev.improvement

import spock.lang.*
/**
* https://practice.geeksforgeeks.org/problems/boolean-matrix-problem/0
*/
class BooleanMatrix extends Specification {

  @Unroll
  def 'test 1'(){
		when:
	  		def arr = [
				[1, 0], 
			  	[0, 0]]
		then:
			updateMatrix(arr) == [
				[1, 1], 
				[1, 0]
			]
  }

  @Unroll
  def 'test 2'(){
		when:
	  		def arr = [
				[0, 0, 0], 
				[0, 0, 1]]
		then:
			updateMatrix(arr) == [[0, 0, 1], [1, 1, 1]]
  }

  @Unroll
  def 'test 3'(){
		when:
	  		def arr = [
				[1, 0, 0],
				[1, 0, 0],
				[1, 0, 0],
				[0, 0, 0]]
		then:
			updateMatrix(arr) == [
				[1, 1, 1], 
				[1, 1, 1], 
				[1, 1, 1], 
				[1, 0, 0]]
  }  

  @Unroll
  def 'test 4'(){
		when:
	  		def arr = [
				  [0, 0, 1, 0, 1, 0, 0],
				  [0, 0, 0, 0, 0, 0, 1],
				  [0, 0, 0, 0, 0, 0, 0],
				  [0, 0, 1, 0, 0, 0, 0],
				  [0, 0, 0, 1, 1, 0, 0],
				  [0, 0, 0, 0, 0, 0, 0],
				  [0, 0, 0, 0, 0, 0, 0],
				  [1, 0, 0, 0, 0, 0, 0]]
		then:
			updateMatrix(arr) == [
				  [1, 1, 1, 1, 1, 1, 1],
				  [1, 1, 1, 1, 1, 1, 1],
				  [1, 0, 1, 1, 1, 0, 1],
				  [1, 1, 1, 1, 1, 1, 1],
				  [1, 1, 1, 1, 1, 1, 1],
				  [1, 0, 1, 1, 1, 0, 1],
				  [1, 0, 1, 1, 1, 0, 1],
				  [1, 1, 1, 1, 1, 1, 1]]
  }

  @Unroll
  def 'test 5'(){
	when:
		def arr = 
		[
			[1, 0, 0],
			[0, 0, 1],
			[0, 0, 0]
		]
	then:
		updateMatrix(arr) == 
		[
			[1, 1, 1], 
			[1, 1, 1], 
			[1, 0, 1]
		]
  }

	public static List<Integer[]> updateMatrix(List<Integer[]> input) {
		List<Integer[]> workingList = new ArrayList<Integer[]>();
		for (int i = 0; i < input.size(); i++) {
			workingList.add(new Integer[input[i].size()]);
			for (int j = 0; j < input[i].size(); j++) {
				workingList[i][j] = 0;
			}
		}

		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input[i].size(); j++) {
				if (input[i][j] == 1) {
					//go through column
					updateColumn(workingList, j);
					updateRow(workingList, i);
				}
			}
		}

		println workingList
		return workingList;
	}

	public static updateColumn(List<Integer[]> inputList, int startCol) {
		for (int i = 0; i < inputList.size(); i++){
			inputList[i][startCol] = 1;
		}
	}

	public static updateRow(List<Integer[]> inputList, int startRow) {
		for (int i = 0; i < inputList[startRow].size(); i++){
			inputList[startRow][i] = 1;
		}
	}



	// iterative, in place modification
	public static List<Integer[]> updateMatrix(List<Integer[]> matrix) {

		for(int r = 0; r < matrix.size(); r++){
			for(int c = 0; c < matrix[r].size(); c++){
				if(matrix[r][c] == 1)
					mark(matrix, r, c)
			}
		}

		for(int r = 0; r < matrix.size(); r++){
			for(int c = 0; c < matrix[r].size(); c++){
				if(matrix[r][c] == 2)
					matrix[r][c] = 1;
			}
		}

		return matrix;
	}

	private static void mark(List<Integer[]> matrix, int row, int col){
		markRow(matrix, row);
		markColumn(matrix, col);
	}

	private static void markRow(List<Integer[]> matrix, int rowIndex){
		for(int c = 0; c < matrix[rowIndex].size(); c++){
			if(matrix[rowIndex][c] != 1)
				matrix[rowIndex][c] = 2
		}
	}

	private static void markColumn(List<Integer[]> matrix, int colIndex){
		for(int r = 0; r < matrix.size(); r++){
			if(matrix[r][colIndex] != 1)
				matrix[r][colIndex] = 2
		}
	}
}