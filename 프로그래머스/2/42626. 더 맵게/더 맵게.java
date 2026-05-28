import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        // 스코빌 지수를 오름차순으로 관리하기 위한 최소 힙
        // PriorityQueue는 기본적으로 가장 작은 값이 먼저 나오는 구조이다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 모든 음식의 스코빌 지수를 우선순위 큐에 넣는다.
        for (int score : scoville) {
            pq.offer(score);
        }

        // 음식을 섞은 횟수
        int count = 0;

        // 가장 맵지 않은 음식의 스코빌 지수가 K보다 작고,
        // 섞을 수 있는 음식이 2개 이상 남아 있을 때 반복한다.
        while (pq.size() >= 2 && pq.peek() < K) {
            // 가장 맵지 않은 음식
            int first = pq.poll();

            // 두 번째로 맵지 않은 음식
            int second = pq.poll();

            // 문제에서 제시한 공식에 따라 두 음식을 섞는다.
            // 새로운 음식의 스코빌 지수 = 가장 맵지 않은 음식 + 두 번째로 맵지 않은 음식 * 2
            int mixed = first + second * 2;

            // 새로 만든 음식을 다시 우선순위 큐에 넣는다.
            pq.offer(mixed);

            // 음식을 한 번 섞었으므로 횟수를 1 증가시킨다.
            count++;
        }

        // 반복이 끝난 뒤 가장 낮은 스코빌 지수가 K 이상이면 성공이다.
        // 아직 K보다 작다면 더 이상 섞을 수 없으므로 -1을 반환한다.
        return pq.peek() >= K ? count : -1;
    }
}