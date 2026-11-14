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
            String operator = splitted[0];
            int number = Integer.parseInt(splitted[1]);
            // 삽입 연산인 경우
            if (operator.equals("I")) {
                // 같은 값을 최대 힙과 최소 힙에 모두 삽입한다.
                // 최대값 삭제와 최소값 삭제를 각각 빠르게 처리하기 위해 두 개의 큐를 사용한다.
                maxPq.offer(number);
                minPq.offer(number);
            }
            // 삭제 연산인 경우
            else {

                // "D 1"인 경우 최댓값을 삭제한다.
                if (number == 1 && !maxPq.isEmpty() && !minPq.isEmpty()) {
                    // 최대 힙이 비어 있지 않을 때만 삭제한다.
                    minPq.remove(maxPq.poll());
                }
                // "D -1"인 경우 최솟값을 삭제한다.
                else if (number == -1 && !maxPq.isEmpty() && !minPq.isEmpty()) {
                    // 최소 힙이 비어 있지 않을 때만 삭제한다.
                    maxPq.remove(minPq.poll());
                }
            }
        }

        // 최대힙, 최소힙 비어 있다면 [0, 0]을 반환한다.
        // 그렇지 않으면 최대 힙에서 최댓값, 최소 힙에서 최솟값을 반환한다.
        int[] answer = new int[2];

        if (maxPq.isEmpty() || minPq.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = maxPq.peek();
            answer[1] = minPq.peek();
        }
        return answer;
    }
}