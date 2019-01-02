package com.daniel.leetcode.solutions.no307;
/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

Your NumArray object will be instantiated and called as such:
NumArray obj = new NumArray(nums);
obj.update(i,val);
int param_2 = obj.sumRange(i,j);

 */

// O(n)
public class NumArray {

    private int[] sum;
    private int[] data;

    public NumArray(int[] nums) {

        data = new int[nums.length];
        for (int i=0; i < data.length; i ++)
            data[i] = nums[i];

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i=1; i < sum.length; i ++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public void update(int index, int val) {
        data[index] = val;
        for(int i = index + 1; i < sum.length; i ++) {
            sum[i] = sum[i - 1] + data[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
