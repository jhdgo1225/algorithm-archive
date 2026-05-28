import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        for (String operation : operations) {
            String[] splitted = operation.split(" ");
            if (splitted[0].equals("I")) {
                maxPq.offer(Integer.parseInt(splitted[1]));
                minPq.offer(Integer.parseInt(splitted[1]));
            } else {
                int pop;
                if (splitted[1].equals("1")) {
                    if (!maxPq.isEmpty()) {
                        pop = maxPq.poll();
                        if (!minPq.isEmpty() && minPq.peek() == pop) minPq.poll();
                    }
                } else {
                    if (!minPq.isEmpty()) {
                        pop = minPq.poll();
                        if (!maxPq.isEmpty() && maxPq.peek() == pop) maxPq.poll();
                    }
                }
            }
        }
        Set<Integer> maxSet = new HashSet<>();
        while (!maxPq.isEmpty()) maxSet.add(maxPq.poll());
        Set<Integer> minSet = new HashSet<>();
        while (!minPq.isEmpty()) minSet.add(minPq.poll());
        Set<Integer> intersectionSet = new HashSet<>(maxSet);
        intersectionSet.retainAll(minSet);
        int[] answer = {(!intersectionSet.isEmpty()) ? Collections.max(intersectionSet) : 0, (!intersectionSet.isEmpty()) ? Collections.min(intersectionSet) : 0};
        return answer;
    }
}