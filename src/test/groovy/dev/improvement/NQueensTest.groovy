package dev.improvement

import org.junit.*

@groovy.transform.CompileStatic
class NQueensTest {
  @Test
  void qcheck_test() {
    assert qcheck([4, 2, 7, 3, 6, 8, 5, 1]) == true
    assert qcheck([2, 5, 7, 4, 1, 8, 6, 3]) == true
    assert qcheck([5, 3, 1, 4, 2, 8, 6, 3]) == false   //(b3 and h3 are on the same row)
    assert qcheck([5, 8, 2, 4, 7, 1, 3, 6]) == false   //(b8 and g3 are on the same diagonal)
    assert qcheck([4, 3, 1, 8, 1, 3, 5, 2]) == false   //(multiple problems)
    assert qcheck([1, 3, 0, 2])
    assert qcheck([0, 1]) == false
    assert qcheck([])
  }

  @Test
  void qfix_test() {
    assert qfix([8, 6, 4, 2, 7, 1, 3, 5]) == [4, 6, 8, 2, 7, 1, 3, 5]
    assert qfix([8, 5, 1, 3, 6, 2, 7, 4]) == [8, 4, 1, 3, 6, 2, 7, 5]
    assert qfix([4, 6, 8, 3, 1, 2, 5, 7]) == [4, 6, 8, 3, 1, 7, 5, 2]
    assert qfix([7, 1, 3, 6, 8, 5, 2, 4]) == [7, 3, 1, 6, 8, 5, 2, 4]
  }
  
  static boolean qcheck(List<Integer> pos){
    for(int i = 0; i < pos.size() - 1; i++){
      for(int j = 1; j < pos.size() - i; j++) {
        if(pos[i] == pos[i + j]
           || pos[i] - j == pos[i + j]
           || pos[i] + j == pos[i + j]){
          return false
        }
      }
    }
    return true
  }

  static List<Integer> qfix(List<Integer> pos){
    def r = new ArrayList<>(pos)
    for(int i=0; i < pos.size()-1; i++){
      for(int j=i+1; j < pos.size(); j++) {
        r.swap(i,j)
        if(qcheck(r)) return r
        r.swap(i, j)
      }
    }
    return null
  }
}
