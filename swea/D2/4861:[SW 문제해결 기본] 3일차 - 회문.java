/*
 * [문제] SWEA 4861 [SW 문제해결 기본] 3일차 - 회문
 * [유형] 완전 탐색 / 문자열 (회문)
 * [핵심 아이디어]
 *   1. NxN 격자의 모든 가로 구간, 세로 구간에서 길이 M 의 부분 문자열이 회문인지 검사한다.
 *   2. direction 으로 가로/세로를 구분하고, 양 끝 문자를 비교해 같지 않으면 즉시 중단한다.
 *   3. 처음 발견한 회문을 반환한다.
 * [시간복잡도] O(N^2 * M)
 * [코테 가이드]
 *   - 회문 검사는 new StringBuilder(s).reverse().toString().equals(s) 로도 된다.
 *   - 격자는 char[][] 로 받아(String.toCharArray) 인덱스로 접근하는 것이 빠르다.
 *   - 탐색 범위는 N - M + 1 까지만 돌아야 인덱스 오류가 나지 않는다.
 */

import java.util.Scanner;

class Solution
{
    static String findPalindrome(char[][] board, int i, int j, int M, int direction) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < M; idx++) {
            int r = i + (direction == 0 ? 0 : idx);
            int c = j + (direction == 0 ? idx : 0);
            int reverseR = i + (direction == 0 ? 0 : M - 1 - idx);
            int reverseC = j + (direction == 0 ? M - 1 - idx : 0);

            char left = board[r][c];
            char right = board[reverseR][reverseC];
            if (left != right) return "";
            sb.append(left);
        }
        return sb.toString();
    }

    static String solution(char[][] board, int N, int M) {
        for (int direction = 0; direction < 2; direction++) {
            int rowLimit = direction == 0 ? N : N - M + 1;
            int colLimit = direction == 0 ? N - M + 1 : N;
            for (int i = 0; i < rowLimit; i++) {
                for (int j = 0; j < colLimit; j++) {
                    String result = findPalindrome(board, i, j, M, direction);
                    if (!result.isEmpty()) return result;
                }
            }
        }
        return "";
    }

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt(), M = sc.nextInt();
            char[][] board = new char[N][];
            for (int i = 0; i < N; i++) board[i] = sc.next().toCharArray();
            System.out.println("#" + test_case + " " + solution(board, N, M));
		}
	}
}
