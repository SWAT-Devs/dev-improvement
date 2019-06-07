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
  }

  static int findLargestSquare(int row, int col, List<String> strArr) {
    int sizeOfSquare = 1;
    while(canFindSquareOfSize(row, col, strArr, sizeOfSquare)) {
      sizeOfSquare++;
    }
    return sizeOfSquare;
  }

  static boolean canFindSquareOfSize(int row, int col, List<String> strArr, int size) {
    if(row + size >= strArr.size() ||
      col + size >= strArr[0].size())
      return false;
    for(int rowTraverse = row; rowTraverse < row + size; rowTraverse++) {
      for(int colTraverse = col; colTraverse < col + size; colTraverse++) {
        if(strArr[rowTraverse][colTraverse] == '0')
          return false;
      }
    }
    return true;
  }
/*
  static int findLargestSquare(int row, int col, List<String> strArr) {
    int maxRowDiff = 0;
    for(int c = col+1; c < strArr[row].size(); c++) {
      if(strArr[row][c]=="1")
        maxRowDiff++;
      else
        break;
    }
    for(int r = row+1; r < strArr.size() && r < row+maxRowDiff; r++) {
     for(int c = col+1; c < strArr[row].size(); c++) {
      if(strArr[row][c]=="1")
        maxRowDiff++;
      else
        break;
      } 
    }
    


    //ROW
    int rowPrime = row;
    int furthestRight = 1;
    while(strArr[rowPrime][col] != "0") {
      furthestRight++;
    }
    //COL
    int colPrime = col;
    int furthestDown = 1;
    while(strArr[row][colPrime] != "0") {
      furthestDown++;
    }
  }

  static int maximalSquare(List<String> strArr) {
    def size = null;
    //Loop 1
    for(int row = 0; row < strArr.size(); row++){
      String r = strArr[row]
      //Loop 2
      for(int col = 0; col < r.length(); col++){
        char c = r[col];
        if(c == "1"){
          if(size == null) size = 1;
          //Loop 3
          def rowHeight = 0
          def maxColCount = 0
          for(int row2 = row;  row2 < strArr.size(); row2++){
            rowHeight++
            //Loop 4
            def colCount = 0
            for(int col2 = col; col2 < r.length(); col2++){
              if(strArr[row2][col2] == "0"){
                row2 = strArr.size()
                break;
              }
              colCount++
            }
            maxColCount = Math.max(maxColCount, colCount)
          }
          size = Math.max(size, Math.min(rowHeight, maxColCount))
        }
      }
    }
    return size  * size 
  }
  */
}