package dev.improvement

import spock.lang.*

class LeapGameTest extends Specification {
  @Unroll
  def 'canWin(#leap, #game) = #expected'() {
    expect:
      canWin(leap, game) == expected
    where:
    leap | game               || expected
    3    | [0, 0, 0, 0, 0]    || true
    5    | [0, 0, 0, 1, 1, 1] || true
    3    | [0, 0, 1, 1, 1, 0] || false
    1    | [0, 1, 0]          || false
    3    | [0, 1, 1, 0, 1, 0, 1] || false
    3    | [0, 1, 0, 0, 1, 0, 1] || true
    4    | [0, 0, 0, 1, 0, 1, 0, 1, 1] || true
  }

  static boolean canWin(int leap, List<Integer> game) {
    Set<Integer> visited = new HashSet<>()
    visited << 0
    return canWin(leap, game, 1, visited) || canWin(leap, game, leap, visited)
  }

  static boolean canWin(int leap, List<Integer> game, int pos, Set<Integer> visited) {
    if(visited.contains(pos)) return false;
    visited << pos
    if(pos < 0) return false;
    if(pos >= game.size()) return true;
    if(game.get(pos) == 1) return false;
    return canWin(leap, game, pos+leap, visited) || canWin(leap, game, pos+1, visited) || canWin(leap, game, pos-1, visited);
  }
}
