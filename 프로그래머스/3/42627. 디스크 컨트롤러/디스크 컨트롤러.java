import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (job1, job2) -> (job1[0] == job2[0]) ? job1[1] - job2[1] : job1[0] - job2[0]);
        // 1st. 작업 시간이 짧은 작업
        // 2nd. 요청 시각이 빠른 작업
        PriorityQueue<int[]> pq = new PriorityQueue<>((job1, job2) -> (job1[1] == job2[1]) ? job1[0] - job2[0] : job1[1] - job2[1]);
        
        int curTime = 0;
        int retTime = 0;
        int idx = 0;
        
        while (idx < jobs.length || !pq.isEmpty()) {
            // job을 하나씩 넣을 때, 현재까지 소요된 시간과 job의 요청된 시점을 비교하며 삽입한다.
            while (idx < jobs.length && jobs[idx][0] <= curTime) {
                pq.add(jobs[idx++]);
            }
            if (pq.isEmpty()) {
                curTime = jobs[idx][0];
                pq.add(jobs[idx++]);
            }
            int[] job = pq.poll();
            curTime += job[1];
            retTime += (curTime - job[0]);
        }
        return retTime / jobs.length;
    }
}