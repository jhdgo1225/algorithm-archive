import java.util.*;

// don't change 'Main' class name and  'public' accessor

class Job {
    int id;
    int reqTime;
    int procTime;
    Job(int id, int reqTime, int procTime) {
        this.id = id;
        this.reqTime = reqTime;
        this.procTime = procTime;
    }
}


class Solution {
    public int solution(int[][] jobs) {
        Job[] jobArr = new Job[jobs.length];
        for (int i=0; i<jobs.length; i++) jobArr[i] = new Job(i, jobs[i][0], jobs[i][1]);
        Arrays.sort(jobArr, (a, b) -> a.reqTime - b.reqTime);
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.procTime == o2.procTime) {
                    if (o1.reqTime == o2.reqTime)
                        return o1.id - o2.id;
                    return o1.reqTime - o2.reqTime;
                }
                return o1.procTime - o2.procTime;
            }
        });
        int curJobReqTime = jobArr[0].reqTime;
        int totalRetTime = 0;
        int idx = 0;
        while (idx < jobs.length) {
            while (idx < jobs.length && jobArr[idx].reqTime <= curJobReqTime) {
                pq.add(jobArr[idx++]);
            }
            if (pq.isEmpty()) {
                curJobReqTime = jobArr[idx].reqTime;
                continue;
            }
            Job priorityJob = pq.poll();
            totalRetTime += (curJobReqTime + priorityJob.procTime) - priorityJob.reqTime;
            curJobReqTime += priorityJob.procTime;
        }
        while (!pq.isEmpty()) {
            Job priorityJob = pq.poll();
            totalRetTime += (curJobReqTime + priorityJob.procTime) - priorityJob.reqTime;
            curJobReqTime += priorityJob.procTime;
        }
        int answer = totalRetTime / jobArr.length;
        return answer;
    }
}