import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] maps) {
        int mapSize = maps.length * maps[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] item = queue.poll();
            int y = item[0];
            int x = item[1];
            int curValue = maps[y][x];
            if (y == maps.length - 1 && x == maps[0].length - 1) return curValue;
            for (int i=0; i<4; i++) {
                int ny = y + dir[i][0];
                int nx = x + dir[i][1];
                if (ny < 0 || ny >= maps.length || nx < 0 || nx >= maps[0].length
                    || maps[ny][nx] == 0)
                    continue;
                if (maps[ny][nx] == 1 || maps[ny][nx] > curValue + 1) {
                    maps[ny][nx] = curValue + 1;
                    queue.add(new int[]{ny, nx});
                }
            }
        }
        return -1;
    }
}