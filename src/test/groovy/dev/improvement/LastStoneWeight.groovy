package dev.improvement

import spock.lang.*

/**
* https://leetcode.com/problems/last-stone-weight/
*/
class LastStoneWeight extends Specification {
    @Unroll
  def 'test 1'(){
    when:
        def arr = [2,7,4,1,8,1]
    then:
        lastStoneWeight(arr) == 1
  }

  def 'test 2'(){
    when:
        def arr = [1,10,3,2,1]
    then:
        lastStoneWeight(arr) == 3
  }

  def 'test 3'(){
    when:
        def arr = [1,2,2,1,2]
    then:
        lastStoneWeight(arr) == 0
  }

  public static int lastStoneWeight(List<Integer> stones) {
      PriorityQueue<Integer> q = new PriorityQueue<>(stones.size(), Collections.reverseOrder());
      q.addAll(stones)

      while(q.size() > 1){
          int res = q.poll() - q.poll(); if(res != 0) { q.add(res)}
          
          
      }
    return q.size() == 0 ? 0 : q.poll()
  }
}