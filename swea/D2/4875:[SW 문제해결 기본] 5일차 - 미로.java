/*
 * [문제] SWEA 4875 [SW 문제해결 기본] 5일차 - 미로
 * [유형] 그래프 / BFS (미로 탐색)
 * [핵심 아이디어]
 *   1. 출발지(2)를 큐에 넣고 상하좌우 4방향으로 탐색한다.
 *   2. 벽(1)이거나 이미 방문한 칸은 건너뛰고, 도착지(3)를 발견하면 1 로 종료한다.
 * [시간복잡도] O(N^2)
 * [코테 가이드]
 *   - 2차원 격자 BFS 의 정석: dy/dx 배열 + 범위 검사 + visited 체크.
 *   - 큐에는 new int[]{y, x} 로 좌표를 묶어 넣는다.
 *   - 입력이 공백 없이 붙어 있으면 next() 로 한 줄을 받아 charAt(j) - '0' 으로 읽는다.
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int[][] board = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            Queue<int[]> queue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                String row = sc.next();
                for (int j = 0; j < N; j++) {
                    board[i][j] = row.charAt(j) - '0';
                    if (board[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }

            int result = 0;
            while (result == 0 && !queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N
                            || board[ny][nx] == 1 || visited[ny][nx]) continue;
                    if (board[ny][nx] == 3) {
                        result = 1;
                        break;
                    }
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx});
                }
            }
            System.out.println("#" + test_case + " " + result);
		}
	}
}
