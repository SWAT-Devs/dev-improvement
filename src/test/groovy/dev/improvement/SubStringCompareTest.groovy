package dev.improvement

import org.junit.*

class SubStringCompareTest {
    @Test
    void test() {
        assert new SmallestAndLargest('ava', 'wel') == getSmallestAndLargest('welcometojava', 3)
    }

  SmallestAndLargest getSmallestAndLargest(String s, int k) {
    if(k < 0 || k > s.length())
      throw new RuntimeException("no");
    String min = null
    String max = null
    for(int i = 0; i < s.length() - k + 1; i++) {
      String s1 = s[i..i + k - 1]
      if(min == null || min > s1) min = s1
      if(max == null || max < s1) max = s1
    }
    new SmallestAndLargest(min, max);
  }

    @groovy.transform.Immutable
    static class SmallestAndLargest {
        String smallest
        String largest
    }
}
