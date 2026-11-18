/*
 * [문제] SWEA 4836 [SW 문제해결 기본] 2일차 - 색칠하기
 * [유형] 구현 / 2차원 배열 칠하기
 * [핵심 아이디어]
 *   1. 10x10 격자에서 직사각형 영역을 색칠하며 각 칸의 상태를 기록한다.
 *   2. 빈 칸이면 해당 색, 다른 색이 이미 있으면 보라색(3)으로 바꾸며 그 순간 보라 칸 수를 1 증가시킨다.
 *   3. 이미 보라색인 칸은 다시 세지 않는다.
 * [시간복잡도] O(N * 100)
 * [코테 가이드]
 *   - 색을 비트(1, 2)로 두면 board[r][c] |= color 후 값이 3 인 칸 수만 세면 된다.
 *   - 중복 카운트 방지 조건(이미 3 이면 건너뜀)을 꼭 넣는다.
 */

import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int[][] board = new int[10][10];
            int purple = 0;

            for (int k = 0; k < N; k++) {
                int r1 = sc.nextInt(), c1 = sc.nextInt();
                int r2 = sc.nextInt(), c2 = sc.nextInt();
                int color = sc.nextInt();

                for (int r = r1; r <= r2; r++) {
                    for (int c = c1; c <= c2; c++) {
                        if (board[r][c] == 0) {
                            board[r][c] = color;
                        } else if (board[r][c] != color && board[r][c] != 3) {
                            board[r][c] = 3;
                            purple++;
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + purple);
		}
	}
}
