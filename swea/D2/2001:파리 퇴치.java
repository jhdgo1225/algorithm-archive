/*
 * [문제] SWEA 2001 파리 퇴치
 * [유형] 누적합 / 완전 탐색
 * [핵심 아이디어]
 *   1. 각 행에 대해 가로 누적합 배열을 만든다.
 *   2. 모든 MxM 윈도우 좌상단 위치를 순회하며 M개 행의 구간합(board[r][c+M-1] - board[r][c-1])을 더한다.
 *   3. 그중 최댓값이 정답이다.
 * [시간복잡도] O(N^2 * M) (2차원 누적합을 쓰면 O(N^2))
 * [코테 가이드]
 *   - 구간합이 반복되면 누적합으로 바꾼다. 2차원 누적합이면 윈도우 합을 O(1) 로 구할 수 있다.
 *   - 누적합은 인덱스를 1부터 쓰고 0번을 0 으로 비워 두면 경계 처리가 쉬워진다.
 *   - 윈도우가 배열 밖으로 나가지 않도록 시작 위치의 범위를 N - M + 1 로 제한한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc=1; tc<=T; tc++) {
            int N = sc.nextInt(), M = sc.nextInt();
            int[][] board = new int[N][N+1];
            for (int i=0; i<N; i++) {
                for (int j=1; j<=N; j++) {
                    int num = sc.nextInt();
                    board[i][j] = board[i][j-1] + num;
                }
            }
            int max = 0;
            for (int i=0; i<N-M+1; i++) {
                for (int j=1; j<=N-M+1; j++) {
                    int tmp = 0;
                    for (int k=0; k<M; k++)
                        tmp += board[i+k][j+M-1] - board[i+k][j-1];
                    if (max < tmp) max = tmp;
                }
            }
            System.out.println("#" + tc + " " + max);
        }
	}
}