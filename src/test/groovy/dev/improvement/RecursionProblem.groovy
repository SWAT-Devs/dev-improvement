package dev.improvement

import org.junit.*
// Found at https://codingbat.com/prob/p145416
class RecursionProblem {
  
  @Test
  void test_the_method() {
    assert groupSum(0, [2, 4, 8], 10)
    assert groupSum(0, [2, 4, 8], 14)
    assert !groupSum(0, [2, 4, 8], 9)
    assert groupSum(0, [2, 4, 8, 1], 3)
    assert groupSum(0, [2, 4, 8, 1, 3], 1)
  }

  /** 
   * Given an array of ints, is it possible to choose a group of some of the
   * ints, such that the group sums to the given target? This is a classic
   * backtracking recursion problem. Once you understand the recursive backtracking
   * strategy in this problem, you can use the same pattern for many problems to
   * search a space of choices. Rather than looking at the whole array, our
   * convention is to consider the part of the array starting at index start and
   * continuing to the end of the array. The caller can specify the whole array
   * simply by passing start as 0. No loops are needed -- the recursive calls
   * progress down the array.  
   */
  boolean groupSum(int start, List<Integer> nums, int target) {
    start >= nums.size() ? target == 0 : groupSum(start + 1, nums, target - nums[start]) || groupSum(start + 1, nums, target)
//    if(start >= nums.size()) return target == 0;
//    return groupSum(start + 1, nums, target - nums[start])
//        || groupSum(start +1, nums, target);
/*    Integer curr =  nums[start]
    if(curr == null) return false
    int newTar = target - curr;
    if(newTar == 0)
      return true
    int newStart = start+1

    if(newStart > nums.size() - 1)
      return false

    if(!groupSum(newStart, nums, newTar)) {
      
      if(!groupSum(newStart + 1, nums, newTar)) {
        return groupSum(newStart + 1, nums, target)
      }
    }
    return true;
*/
  }
}
