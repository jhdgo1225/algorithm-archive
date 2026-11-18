/*
 * [문제] SWEA 1209 [SW 문제해결 기본] 2일차 - Sum
 * [유형] 2차원 배열 / 단순 구현
 * [핵심 아이디어]
 *   1. 입력을 읽으면서 행 합, 열 합, 두 대각선 합을 동시에 누적한다.
 *   2. 주대각선은 i == j, 반대 대각선은 i + j == N - 1 이다.
 *   3. 행/열/대각선 합 중 최댓값을 출력한다.
 * [시간복잡도] O(N^2)
 * [코테 가이드]
 *   - 입력과 동시에 합을 누적하면 배열을 다시 돌 필요가 없다.
 *   - 대각선 조건을 else if 로 묶으면 N 이 홀수일 때 중앙 칸이 반대 대각선에서 빠진다. 두 조건은 따로 if 로 검사한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
        while (T-- > 0) {
            int tc = sc.nextInt();
            int[][] buildings = new int[100][100];
            int[] rowSum = new int[100];
            int[] colSum = new int[100];
            int[] diagonalSum = new int[2];
            int viewCount = 0;
            for (int i=0; i<100; i++) {
                for (int j=0; j<100; j++) {
                    int tmp = sc.nextInt();
                    colSum[j] += tmp;
                    rowSum[i] += tmp;
                    if (i == j) diagonalSum[0] += tmp;
                    if (i == 100 - j - 1) diagonalSum[1] += tmp;
                }
            }
            int maxResult = Integer.MIN_VALUE;
            for (int i=0; i<100; i++) {
                maxResult = Math.max(maxResult, rowSum[i]);
                maxResult = Math.max(maxResult, colSum[i]);
            }
            maxResult = Math.max(maxResult, Math.max(diagonalSum[0], diagonalSum[1]));
            System.out.println("#" + tc + " " + maxResult);
        }
	}
}