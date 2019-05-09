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
    ["0111", "1111", "1111", "1111"]     || 9
    ["0111", "1101", "0111"]             || 1
    ["0000", "0110", "0110", "0000"]     || 4
    ["10100",
     "10111",
     "11111",
     "10010"] || 4
  }

  static int maximalSquare(List<String> strArr){
    int max = 0
    for(int r1 = 0; r1 < strArr.size() - max; r1++){
      for(int c1 = 0; c1 < strArr[r1].size() - max; c1++) {
        if(strArr[r1][c1] == '0') break
        int x = 0;
        for(int c2 = c1; c2 < strArr[r1].size() && strArr[r1][c2]=='1'; c2++)
          x++
        int y = 1
        for(int r2 = r1 +1; r2 < strArr.size() && r2 - r1 < x; r2++){
          int x1 = 0
          for(int c3 = c1; c3 < strArr[r2].size() && c3 - c1 < x && strArr[r2][c3] == '1'; c3++){
            x1++
          }
          x = Math.min(x, x1)
          if(x1 == 0) break
          y++
        }
        max = Math.max(max, Math.min(x, y))
      }
    }
    return max * max
  }
  
  static int maximalSquare1(List<String> strArr) {
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
}
