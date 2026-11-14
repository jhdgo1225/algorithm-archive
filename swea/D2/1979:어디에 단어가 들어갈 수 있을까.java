/*
 * [문제] SWEA 1979 어디에 단어가 들어갈 수 있을까
 * [유형] 구현 / 연속 구간 탐색
 * [핵심 아이디어]
 *   1. 각 행과 각 열을 따라가며 연속된 1(흰 칸)의 길이를 센다.
 *   2. 0 을 만났을 때(또는 줄이 끝났을 때) 연속 길이가 정확히 K 이면 카운트한다.
 *   3. isRow 플래그 하나로 행/열 탐색을 한 함수에서 처리한다.
 * [시간복잡도] O(N^2)
 * [코테 가이드]
 *   - '정확히 K 길이'는 K 이상이 아니라 K 와 같을 때만 센다.
 *   - 줄 끝에서 마지막 구간을 정산하는 부분을 빠뜨리기 쉽다(끝에 0 을 하나 붙이는 방법도 있다).
 *   - 행/열 처리를 중복 코드로 쓰지 말고 전치(transpose)하거나 플래그로 묶는다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	private static int findKWord(int[][] board, int cur, int K, boolean isRow) {
        int cnt = 0, N = board.length;
        boolean isWhite = false;
        int len = 0, idx = 0, value;
        while (idx < N) {
            if (isRow) value = board[cur][idx];
            else value = board[idx][cur];
            if (!isWhite && value == 1) isWhite = true;
            else if (isWhite && value == 0) {
                isWhite = false;
                if (len == K) cnt++;
                len = 0;
            }
            if (isWhite) len++;
            idx++;
        }
        if (isWhite && len == K) cnt++;
        return cnt;
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case=1; test_case<=T; test_case++) {
            int cnt = 0, N = sc.nextInt(), K = sc.nextInt();
            int[][] board = new int[N][N];
            for (int i=0; i<N; i++) for (int j=0; j<N; j++) board[i][j] = sc.nextInt();
            for (int i=0; i<N; i++) {
                cnt += findKWord(board, i, K,true);
                cnt += findKWord(board, i, K,false);
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}