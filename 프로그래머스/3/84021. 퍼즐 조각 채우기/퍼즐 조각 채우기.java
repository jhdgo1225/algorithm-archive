import java.util.*;

class Solution {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        List<List<int[]>> spaces = extract(game_board, 0);
        List<List<int[]>> pieces = extract(table, 1);
        boolean[] used = new boolean[pieces.size()];
        int answer = 0;

        for (List<int[]> space : spaces) {
            for (int i = 0; i < pieces.size(); i++) {
                if (used[i]) continue;
                List<int[]> piece = pieces.get(i);
                if (piece.size() != space.size()) continue;

                List<int[]> cur = piece;
                boolean matched = false;
                for (int r = 0; r < 4; r++) {
                    if (isSame(cur, space)) { matched = true; break; }
                    cur = rotate(cur);
                }
                if (matched) {
                    used[i] = true;
                    answer += space.size();
                    break;
                }
            }
        }
        return answer;
    }

    private List<List<int[]>> extract(int[][] board, int target) {
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        List<List<int[]>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || board[i][j] != target) continue;
                List<int[]> shape = new ArrayList<>();
                shape.add(new int[] {i, j});
                visited[i][j] = true;
                dfs(shape, i, j, board, visited, target);
                normalize(shape);
                result.add(shape);
            }
        }
        return result;
    }

    private void dfs(List<int[]> list, int x, int y, int[][] board, boolean[][] visited, int target) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
            if (board[nx][ny] != target || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            list.add(new int[] {nx, ny});
            dfs(list, nx, ny, board, visited, target);
        }
    }

    // 좌상단을 (0,0)으로 평행이동 + (x, y) 오름차순 정렬
    private void normalize(List<int[]> shape) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (int[] p : shape) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }
        for (int[] p : shape) {
            p[0] -= minX;
            p[1] -= minY;
        }
        shape.sort((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
    }

    // 시계 방향 90도 회전: (x, y) -> (y, -x), 이후 다시 정규화
    private List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] p : shape) rotated.add(new int[] {p[1], -p[0]});
        normalize(rotated);
        return rotated;
    }

    private boolean isSame(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) return false;
        }
        return true;
    }
}
