import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int s : scoville) pq.add((long) s);

        int mixCount = 0;
        while (pq.peek() < K) {
            if (pq.size() < 2) return -1;
            long least = pq.poll();
            long second = pq.poll();
            pq.add(least + second * 2);
            mixCount++;
        }
        return mixCount;
    }
}