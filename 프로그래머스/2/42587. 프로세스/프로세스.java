import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int curLoc = location;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i : priorities) queue.add(i);
        Arrays.sort(priorities);
        int size = priorities.length - 1;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (i == priorities[size - answer]) {
                answer++;
                if (--curLoc < 0) break;
            } else {
                queue.add(i);
                if (--curLoc < 0) curLoc = queue.size() - 1;
            }
        }
        return answer;
    }
}