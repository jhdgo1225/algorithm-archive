/*
 * [문제] SWEA 1961 숫자 배열 회전
 * [유형] 구현 / 2차원 배열 회전
 * [핵심 아이디어]
 *   1. 시계 방향 90도 회전: (i, j) -> (j, N-1-i).
 *   2. 90도 회전을 3번 반복하며 각 단계의 행을 문자열로 저장해 90, 180, 270도 결과를 얻는다.
 *   3. 출력은 같은 행의 세 결과를 공백으로 이어서 한다.
 * [시간복잡도] O(N^2)
 * [코테 가이드]
 *   - 회전 공식 암기: 시계 90도 (i,j)->(j,N-1-i), 반시계 90도 (i,j)->(N-1-j,i), 180도 (i,j)->(N-1-i,N-1-j).
 *   - 회전은 원본을 덮어쓰지 말고 임시 배열에 쓴 뒤 복사한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    private static void solution(int[][] board, String[] arr) {
        int[][] tmp = new int[board.length][board[0].length];
        for (int i=0; i<board.length; i++)
            for (int j=0; j<board[0].length; j++)
                tmp[j][board.length - i - 1] = board[i][j];
        for (int i=0; i<board.length; i++) {
            arr[i] = "";
            for (int j=0; j<board[0].length; j++) {
                board[i][j] = tmp[i][j];
                arr[i] += tmp[i][j];
            }
        }
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case=1; test_case<=T; test_case++) {
            int N = sc.nextInt();
            int[][] board = new int[N][N];
            String[][] arr = new String[3][N];
            for (int i=0; i<N; i++) for (int j=0; j<N; j++) board[i][j] = sc.nextInt();
            for (int i=0; i<3; i++) solution(board, arr[i]);
            System.out.println("#" + test_case);
            for (int i=0; i<N; i++) {
                for (int j=0; j<3; j++) System.out.print(arr[j][i] + " ");
                System.out.println();
            }
        }
	}
}