package dev.improvement

import org.junit.*

/**
 * From https://codejam.withgoogle.com/2018/challenges/00000000000000cb/dashboard
 */
@groovy.transform.CompileStatic
class SavingTheUniverseAgainTest {
  @Test
  void can_we_hack_it() {
    assert howManySwaps(1, 'CS')     == 1
    assert howManySwaps(2, 'CS')     == 0
    assert howManySwaps(1, 'SS')     == -1 //IMPOSSIBLE
    assert howManySwaps(6, 'SCCSSC') == 2
    assert howManySwaps(2, 'CC')     == 0
    assert howManySwaps(3, 'CSCSS')  == 5
  }

  int howManySwaps(int d, String p) {
    -1
  }
}
