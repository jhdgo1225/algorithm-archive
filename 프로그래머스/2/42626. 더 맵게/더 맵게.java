import java.util.PriorityQueue;

class Solution {
    /*
     * PriorityQueue 자료구조 설명
     *
     * PriorityQueue는 우선순위가 높은 데이터가 먼저 나오는 자료구조이다.
     * Java의 PriorityQueue는 기본적으로 "최소 힙" 구조로 동작한다.
     *
     * 즉, 숫자를 넣으면 가장 작은 값이 가장 높은 우선순위를 가지며,
     * pq.peek() 또는 pq.poll()을 했을 때 가장 작은 값이 먼저 나온다.
     *
     * 주요 메서드:
     * - offer(value): 값을 우선순위 큐에 추가한다.
     * - peek(): 우선순위가 가장 높은 값을 확인한다. 제거하지는 않는다.
     * - poll(): 우선순위가 가장 높은 값을 꺼내고 제거한다.
     * - isEmpty(): 큐가 비어 있는지 확인한다.
     * - size(): 큐에 들어 있는 원소 개수를 확인한다.
     *
     * 이 문제에서는 매번 가장 스코빌 지수가 낮은 음식 2개를 꺼내야 한다.
     * 일반 배열이나 리스트를 사용하면 매번 최솟값을 직접 찾아야 하지만,
     * PriorityQueue를 사용하면 가장 작은 값을 빠르게 꺼낼 수 있다.
     *
     * 따라서 "가장 작은 값 2개를 반복해서 꺼내는 문제"에 PriorityQueue가 적합하다.
     */

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