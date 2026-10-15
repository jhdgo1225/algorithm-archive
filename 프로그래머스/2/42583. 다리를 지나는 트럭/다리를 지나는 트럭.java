import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> bridge = new LinkedList<>();
        int time = 0, cur = 0;
        for (int i=0; i<truck_weights.length; i++) {
            time++;
            int w = truck_weights[i];
            while (!bridge.isEmpty() && bridge.peek()[1] <= time) {
                cur -= bridge.poll()[0];
            }
            while (cur + w > weight || bridge.size() >= bridge_length) {
                int[] front = bridge.poll();
                time = front[1];
                cur -= front[0];
            }
            bridge.offer(new int[]{w, time + bridge_length});
            cur += w;
        }
        return time + bridge_length;
    }
}