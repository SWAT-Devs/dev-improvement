package dev.improvement

import spock.lang.*

/**
 * https://www.coderbyte.com/editor/guest:Maximal%20Square:Java
 */
class MaximalSquareTest extends Specification {

  @Unroll
  def 'Maximal Square'(){
    expect:
      maximalSquare(arr) == expected
    where:
    arr                                  || expected
    ["0111", 
     "1111", 
     "1111", 
     "1111"]     || 9
    ["0111", 
     "1101", 
     "0111"]             || 1
    ["0000", 
     "0110", 
     "0110", 
     "0000"]     || 4
    ["10100",
     "10111",
     "11111",
     "10010"] || 4
  }

  static int maximalSquare(List<String> strArr) {
    int biggestSquare = 0;
    for(int row = 0; row < strArr.size(); row++) {
      for(int col = 0; col < strArr[row].size(); col++) {
        if(strArr[row][col] == "1") {
          biggestSquare = Math.max(biggestSquare, findLargestSquare(row, col, strArr))
        }
      }
    }
    return biggestSquare;
  }

  static int findLargestSquare(int row, int col, List<String> strArr) {
    int sizeOfSquare = 1;
    while(canFindSquareOfSize(row, col, strArr, sizeOfSquare+1)) {
      sizeOfSquare++;
    }
    return Math.pow(sizeOfSquare, 2);
  }

  static boolean canFindSquareOfSize(int row, int col, List<String> strArr, int size) {
    if(row + size > strArr.size() ||
      col + size > strArr[0].size())
      return false;
    for(int rowTraverse = row; rowTraverse < row + size; rowTraverse++) {
      for(int colTraverse = col; colTraverse < col + size; colTraverse++) {
        if(strArr[rowTraverse][colTraverse] == '0')
          return false;
      }
    }
    return true;
  }
}