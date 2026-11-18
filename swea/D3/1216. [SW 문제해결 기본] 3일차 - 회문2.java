/*
 * [문제] SWEA 1216 [SW 문제해결 기본] 3일차 - 회문2
 * [유형] 완전 탐색 / 문자열
 * [핵심 아이디어]
 *   1. 100x100 판의 모든 시작점과 모든 길이에 대해 가로/세로 회문인지 검사한다.
 *   2. 회문이면 그 길이로 최댓값을 갱신한다.
 * [시간복잡도] O(N^4) (N = 100, 실제 비교는 L/2 번이라 통과 가능)
 * [코테 가이드]
 *   - 길이 범위는 1 ~ N 이다. 반복 조건을 < N 으로 쓰면 길이 N 짜리 회문을 놓친다(<= N 이어야 한다).
 *   - 긴 길이부터 내려오며 검사하고 처음 찾는 순간 멈추면 훨씬 빠르다.
 *   - 중심 확장법(각 칸을 중심으로 양쪽으로 넓히기)을 쓰면 O(N^3) 으로 줄어든다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T=10;
        while (T-- > 0)
        {
            int test_case = sc.nextInt();
            sc.nextLine();
            String[][] board = new String[100][100];
            for (int i=0; i<100; i++) {
                String row = sc.nextLine();
                for (int j=0; j<100; j++) {
                    board[i][j] = row.charAt(j)+"";
                }
            }
            int maxPalindrome = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    for (int maxLength = 1; maxLength <= board.length; maxLength++) {
                        boolean verticalFlag = true, horizontalFlag = true;
                        if (j + maxLength > board[0].length) verticalFlag = false;
                        else {
                            for (int k = 0; (k < maxLength / 2); k++) {
                                if (!board[i][j + k].equals(board[i][j + maxLength - k - 1])) {
                                    verticalFlag = false;
                                    break;
                                }
                            }
                        }
                        if (i + maxLength > board.length) horizontalFlag = false;
                        else {
                            for (int k = 0; k < maxLength / 2; k++) {
                                if (!board[i + k][j].equals(board[i + maxLength - k - 1][j])) {
                                    horizontalFlag = false;
                                    break;
                                }
                            }
                        }
                        if (verticalFlag || horizontalFlag) maxPalindrome = Math.max(maxPalindrome, maxLength);
                    }
                }
            }
            System.out.println("#" + test_case + " " + maxPalindrome);
        }
	}
}