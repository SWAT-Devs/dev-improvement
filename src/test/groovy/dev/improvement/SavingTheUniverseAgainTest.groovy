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
    int swapCount = 0;

    char[] cmd = p.toCharArray()
    
    if(strength(cmd) <= d)
      return swapCount;

    int cntS = 0;
    for(int i=0; i< cmd.length; i++)
      if(cmd[i] == 'S')
        cntS++

    if(cntS > d)
      return -1

    while(swap(cmd)){
      swapCount++
      if(strength(cmd) <= d)
        return swapCount;
    }
    -1
  }
  
  boolean swap(char[] cmd){
    int lastS = cmd.length - 1
    for(; lastS>= 0; lastS--)
      if(cmd[lastS] == 'S')
        break;
    
    while(cmd[lastS - 1] != 'C'){
      lastS--;
      if(lastS == 0)
        return false;
    }
    
    cmd[lastS] = 'C';
    cmd[lastS - 1] = 'S';
    return true
  }

  int strength(char[] command){
    int power = 1;
    int total = 0;
    for(char c : command){
      if(c == 'C') power =power << 1;
      if(c == 'S') total += power;
    }
    return total;
  }
}
