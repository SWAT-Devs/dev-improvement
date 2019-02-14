package dev.improvement

import org.junit.*

class SubStringCompareTest {
    @Test
    void test() {
        assert new SmallestAndLargest('ava', 'wel') == getSmallestAndLargest('welcometojava', 3)
    }

    SmallestAndLargest getSmallestAndLargest(String s, int k) {
        def allSubs = new TreeSet<String>()
        if(k < 0 || k > s.length())
            throw new RuntimeException("no");
        for(int i = 0; i < s.length() - k + 1; i++) {
            allSubs.add(s[i..i + k -1]);
        }
        new SmallestAndLargest(allSubs.first(), allSubs.last());
    }

    @groovy.transform.Immutable
    static class SmallestAndLargest {
        String smallest
        String largest
    }
}