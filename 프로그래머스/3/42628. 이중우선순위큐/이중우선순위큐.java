import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        // 최댓값을 빠르게 꺼내기 위한 최대 힙
        // Collections.reverseOrder()를 사용하면 큰 값이 먼저 나온다.
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        // 최솟값을 빠르게 꺼내기 위한 최소 힙
        // PriorityQueue의 기본 정렬은 오름차순이다.
        PriorityQueue<Integer> minPq = new PriorityQueue<>();

        // 모든 연산 명령어를 하나씩 처리한다.
        for (String operation : operations) {
            // 명령어는 "I 숫자" 또는 "D 1", "D -1" 형태이므로 공백 기준으로 분리한다.
            String[] splitted = operation.split(" ");

            // 삽입 연산인 경우
            if (splitted[0].equals("I")) {
                // 같은 값을 최대 힙과 최소 힙에 모두 삽입한다.
                // 최대값 삭제와 최소값 삭제를 각각 빠르게 처리하기 위해 두 개의 큐를 사용한다.
                maxPq.offer(Integer.parseInt(splitted[1]));
                minPq.offer(Integer.parseInt(splitted[1]));
            } 
            // 삭제 연산인 경우
            else {
                int pop;

                // "D 1"인 경우 최댓값을 삭제한다.
                if (splitted[1].equals("1")) {
                    // 최대 힙이 비어 있지 않을 때만 삭제한다.
                    if (!maxPq.isEmpty()) {
                        // 최대 힙에서 가장 큰 값을 꺼낸다.
                        pop = maxPq.poll();

                        // 최소 힙의 최솟값이 방금 삭제한 값과 같다면
                        // 최소 힙에서도 함께 제거한다.
                        if (!minPq.isEmpty() && minPq.peek() == pop) {
                            minPq.poll();
                        }
                    }
                } 
                // "D -1"인 경우 최솟값을 삭제한다.
                else {
                    // 최소 힙이 비어 있지 않을 때만 삭제한다.
                    if (!minPq.isEmpty()) {
                        // 최소 힙에서 가장 작은 값을 꺼낸다.
                        pop = minPq.poll();

                        // 최대 힙의 최댓값이 방금 삭제한 값과 같다면
                        // 최대 힙에서도 함께 제거한다.
                        if (!maxPq.isEmpty() && maxPq.peek() == pop) {
                            maxPq.poll();
                        }
                    }
                }
            }
        }

        // 최대 힙에 남아 있는 값들을 Set에 저장한다.
        Set<Integer> maxSet = new HashSet<>();
        while (!maxPq.isEmpty()) {
            maxSet.add(maxPq.poll());
        }

        // 최소 힙에 남아 있는 값들을 Set에 저장한다.
        Set<Integer> minSet = new HashSet<>();
        while (!minPq.isEmpty()) {
            minSet.add(minPq.poll());
        }

        // 두 Set의 교집합을 구한다.
        // 두 힙에 공통으로 남아 있는 값만 실제로 남아 있는 값이라고 판단한다.
        Set<Integer> intersectionSet = new HashSet<>(maxSet);
        intersectionSet.retainAll(minSet);

        // 교집합이 비어 있지 않다면 최댓값과 최솟값을 반환한다.
        // 비어 있다면 큐가 비어 있는 상태로 보고 [0, 0]을 반환한다.
        int[] answer = {
            (!intersectionSet.isEmpty()) ? Collections.max(intersectionSet) : 0,
            (!intersectionSet.isEmpty()) ? Collections.min(intersectionSet) : 0
        };

        return answer;
    }
}