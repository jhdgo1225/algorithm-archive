/*
 * [문제] SWEA 1974 스도쿠 검증
 * [유형] 구현 / 검증 (중복 체크)
 * [핵심 아이디어]
 *   1. 각 행, 각 열, 각 3x3 박스에 대해 boolean[10] 로 숫자 중복 여부를 확인한다.
 *   2. 하나라도 중복이면 0, 모두 통과하면 1.
 * [시간복잡도] O(81 * 3)
 * [코테 가이드]
 *   - '1~9가 한 번씩'은 중복 체크 배열 또는 비트마스크(mask |= 1 << v)로 검사한다.
 *   - 3x3 박스 순회는 시작 좌표를 3씩 증가시키고 내부에서 (k, l) 오프셋을 더하는 패턴이다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    private static int solution(int[][] board) {
        for (int i=0; i<9; i++) {
            boolean[] check = new boolean[10];
            for (int j=0; j<9; j++) {
                if (check[board[i][j]]) return 0;
                check[board[i][j]] = true;
            }
        }
        for (int j=0; j<9; j++) {
            boolean[] check = new boolean[10];
            for (int i=0; i<9; i++) {
                if (check[board[i][j]]) return 0;
                check[board[i][j]] = true;
            }
        }
        for (int i=0; i<9; i+=3) {
            for (int j=0; j<9; j+=3) {
                boolean[] check = new boolean[10];
                for (int k=0; k<3; k++) {
                    for (int l=0; l<3; l++) {
                        if (check[board[i+k][j+l]]) return 0;
                        check[board[i+k][j+l]] = true;
                    }
                }
            }
        }
        return 1;
    }
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int[][] board = new int[9][9];
            for (int i=0; i<9; i++) for (int j=0; j<9; j++) board[i][j] = sc.nextInt();
            int isPossible = solution(board);
            System.out.println("#" + test_case + " " + isPossible);
		}
	}
}