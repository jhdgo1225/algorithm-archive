import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int max = -1;
        List<Integer> stack = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<progresses.length; i++) {
            int remainProgress = 100 - progresses[i];
            int day = remainProgress / speeds[i] + (remainProgress % speeds[i] > 0 ? 1 : 0);
            if (max == -1 || max < day) {
                if (!stack.isEmpty()) {
                    result.add(stack.size());
                    stack.clear();
                }
                max = day;
            }
            stack.add(day);
        }
        if (!stack.isEmpty()) {
            result.add(stack.size());
            stack.clear();
        }
        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}