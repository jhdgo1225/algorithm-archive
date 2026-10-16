import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> phoneketmon = new HashSet<>();
        for (int num: nums) {
            phoneketmon.add(num);
        }
        answer = phoneketmon.size();
        if (phoneketmon.size() > nums.length / 2)  answer = nums.length / 2;
        return answer;
    }
}