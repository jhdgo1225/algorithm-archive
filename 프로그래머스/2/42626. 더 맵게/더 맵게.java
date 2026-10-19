import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = -1;
        int cnt = 0;
        for (int food : scoville) pq.add(food);
        while (pq.size() >= 2) {
            if (pq.peek() >= K) {
                answer = cnt;
                break;
            }
            int food1 = pq.poll();
            int food2 = pq.poll();
            pq.add(food1 + food2 * 2);
            cnt++;
        }
        if (pq.peek() >= K) answer = cnt;
        return answer;
    }
}