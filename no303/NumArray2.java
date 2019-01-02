package com.daniel.leetcode.solutions.no303;

/*

303. Range Sum Query - Immutable

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
1. You may assume that the array does not change.
2. There are many calls to sumRange function.

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */


public class NumArray2 {

    // Pre-process. sums[1] = nums[1]
    // sums[2] = nums[1] + nums[2]
    // sums[2] = sums[1] + nums[2]
    // sums[3] = sums[2] + nums[3]
    // sums[i] = ∑ nums[0 ... i - 1]  i >= 1
    private int[] sums;

    public NumArray2(int[] nums) {
        sums = new int[nums.length + 1];
        sums[0] = 0;
        for(int i=1; i < sums.length; i ++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }


}
