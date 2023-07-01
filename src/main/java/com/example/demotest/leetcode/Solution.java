package com.example.demotest.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;
        for(int i = 0; i < len; i++){
            int minus = target - nums[i];
            if(map.containsKey(minus)){
                int aIndex = i;
                int bIndex = map.get(minus);
                System.out.println(aIndex + "--------" + bIndex);
                return new int[]{bIndex,aIndex};
            }else{
                map.put(nums[i],i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,7,11,15};
        int target = 9;
        solution.twoSum(nums,target);
    }
}
