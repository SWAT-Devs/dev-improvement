package dev.improvement

import spock.lang.*

/**
 * From https://codejam.withgoogle.com/2018/challenges/00000000000000cb/dashboard
 */
abstract class SavingTheUniverseAgain extends Specification {
  HowMany impl
  @Unroll
  def "howMany (#shield, '#program') = #expected"() {
    expect:
      impl.howMany(shield, program) == expected
    where:
      shield | program                          || expected
      1      | 'CS'                             || 1
      2      | 'CS'                             || 0
      1      | 'SS'                             || -1
      6      | 'SCCSSC'                         || 2
      2      | 'CC'                             || 0
      3      | 'CSCSS'                          || 5
      3      | 'CSSS'                           || 3
      15     | 'CCCCCCCCCCCCCCCSSSSSSSSSSSSSSS' || 225
  }
}

class SavingTheUniverseAgainMichaelsTest extends SavingTheUniverseAgain {
  def setup() { impl = new Michaels() }
}


class SavingTheUniverseAgainGroupsTest extends SavingTheUniverseAgain {
  def setup() { impl = new Groups() }
}

abstract class HowMany {
  abstract int howMany(int d, String p)
  
  int strength(char[] command){
    int power = 1;
    int total = 0;
    for(char c : command){
      if(c == 'C') power =power << 1;
      if(c == 'S') total += power;
    }
    return total;
  }

  @Override String toString() { getClass().simpleName }
}

class Groups extends HowMany {
  @Override
  int howMany(int d, String p) {
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
}

class Michaels extends HowMany {
  @Override
  int howMany(int shieldStr, String sCmd) {
		char[] cmd = sCmd.toCharArray();
		int swapCount = 0;
		int laserPower = strength(cmd);
		int lastShotInd = cmd.length - 1;
		for (; lastShotInd > 0; lastShotInd--) {
			if (cmd[lastShotInd] == 'S' && cmd[lastShotInd - 1] == 'C')
				break;

		}

		int lastShotPower = 1;

		for (int i = 0; i < lastShotInd; i++){
			if (cmd[i] == 'C')
				lastShotPower *= 2;
    }
		while (laserPower > shieldStr) {
			if (lastShotInd == 0)
				return -1;

			cmd[lastShotInd] = 'C';
			cmd[lastShotInd - 1] = 'S';
			laserPower -= (int)(lastShotPower / 2);
			swapCount++;

			if (lastShotInd < cmd.length - 1 && cmd[lastShotInd + 1] == 'S')
				lastShotInd++;
			else {
				while (lastShotInd > 0 && cmd[lastShotInd - 1] == 'S')
					lastShotInd--;
				lastShotPower -= (int)(lastShotPower / 2);
			}
		}
		return swapCount;
	}
}
