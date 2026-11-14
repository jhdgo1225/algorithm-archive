import java.util.*;

class Solution {
    private static final int REQUEST = 0;
    private static final int DURATION = 1;

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[REQUEST], b[REQUEST]));

        // 소요 시간이 짧은 순, 같으면 요청 시각이 빠른 순
        PriorityQueue<int[]> waiting = new PriorityQueue<>((a, b) ->
                a[DURATION] != b[DURATION]
                        ? Integer.compare(a[DURATION], b[DURATION])
                        : Integer.compare(a[REQUEST], b[REQUEST]));

        int now = 0;
        int totalTurnaround = 0;
        int next = 0;

        for (int done = 0; done < jobs.length; done++) {
            // 대기 작업이 없으면 다음 요청 시각까지 기다린다
            if (waiting.isEmpty()) {
                now = Math.max(now, jobs[next][REQUEST]);
            }
            // 지금까지 요청된 작업을 모두 대기열에 넣는다
            while (next < jobs.length && jobs[next][REQUEST] <= now) {
                waiting.add(jobs[next++]);
            }

            int[] job = waiting.poll();
            now += job[DURATION];
            totalTurnaround += now - job[REQUEST];
        }
        return totalTurnaround / jobs.length;
    }
}