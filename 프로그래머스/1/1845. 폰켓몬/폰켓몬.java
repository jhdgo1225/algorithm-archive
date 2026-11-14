import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int half = nums.length >> 1;
        return (set.size() >= half) ? half : set.size();
    }
}