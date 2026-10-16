import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int len = set.toArray(new Integer[0]).length;
        int half = nums.length / 2;
        return (len >= half) ? half : len;
    }
}