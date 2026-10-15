import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int[] popOrder = new int[priorities.length];
        int[] cntArr = new int[10];
        List<int[]> queue = new ArrayList<>();
        for (int i=0; i<priorities.length; i++) {
            queue.add(new int[]{i, priorities[i]});
            cntArr[priorities[i]]++;
        }
        int pointer = 9;
        int priority = 1;
        while (pointer > 0) {
            if (cntArr[pointer] == 0) {
                pointer--;
                continue;
            }
            int[] front = queue.remove(0);
            if (pointer == front[1]) {
                popOrder[front[0]] = priority++;
                cntArr[pointer]--;
            } else {
                queue.add(front);
            }
        }
        return popOrder[location];
    }
}