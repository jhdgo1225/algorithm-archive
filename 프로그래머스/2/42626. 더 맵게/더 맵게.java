import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) pq.offer(Long.valueOf(scoville[i]));
        for (int i=0; pq.size() > 1 && pq.peek() < K; i++){
            long top = pq.poll();
            long second = pq.poll();
            pq.add(top + second * 2);
            answer++;
        }
        if (pq.size() > 0 && pq.peek() < K) answer = -1;
        return answer;
    }
}