/*
 * [문제] SWEA 1215 [SW 문제해결 기본] 3일차 - 회문1
 * [유형] 완전 탐색 / 문자열
 * [핵심 아이디어]
 *   1. 8x8 판의 모든 칸을 시작점으로 잡는다.
 *   2. 가로 방향, 세로 방향으로 길이 L 인 구간이 판 안에 들어오면 양 끝에서 안쪽으로 비교한다.
 *   3. 회문이면 개수를 늘린다.
 * [시간복잡도] O(8 * 8 * L)
 * [코테 가이드]
 *   - 회문 판별은 k 와 L-1-k 를 L/2 번만 비교하면 된다.
 *   - 가로/세로를 같은 틀로 쓰고 인덱스만 바꾸면 실수가 줄어든다.
 *   - 문자를 String 으로 저장하면 equals 비교가 필요하다. char[][] 로 두면 == 로 비교할 수 있고 더 빠르다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T=10;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int maxLength = sc.nextInt();
            sc.nextLine();
            String[][] board = new String[8][8];
            for (int i=0; i<8; i++) {
                String row = sc.nextLine();
                for (int j=0; j<8; j++) {
                    board[i][j] = row.charAt(j)+"";
                }
            }
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
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
                    if (verticalFlag) {
                        count++;
                    }
                    if (horizontalFlag) {
                        count++;
                    }
                }
            }
            System.out.println("#" + test_case + " " + count);
        }
	}
}