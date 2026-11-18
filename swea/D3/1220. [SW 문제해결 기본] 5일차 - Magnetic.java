/*
 * [문제] SWEA 1220 [SW 문제해결 기본] 5일차 - Magnetic
 * [유형] 시뮬레이션 / 스택
 * [핵심 아이디어]
 *   1. N 극은 위, S 극은 아래에 있으므로 N 극 자성체(1)는 아래로, S 극 자성체(2)는 위로 움직인다.
 *   2. 각 열을 위에서 아래로 보며 1 다음에 2 가 나오면 교착 상태 하나가 생긴다.
 *   3. 1 이 쌓여 있는 상태에서 2 를 만나면 개수를 늘리고 상태를 비운다.
 * [시간복잡도] O(N^2)
 * [코테 가이드]
 *   - 실제로 자성체를 움직일 필요가 없다. 열마다 '1 → 2' 전환 횟수만 세면 된다.
 *   - 스택에 쌓은 값은 쓰지 않으므로 boolean 플래그 하나로 바꿔도 같다.
 *   - 맨 위의 2 나 맨 아래의 1 은 판 밖으로 빠지므로 세지 않는다는 점을 확인한다.
 */

import java.util.Scanner;
import java.util.Stack;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[][] board = new int[N][N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            int count = 0;
            for (int x = 0; x < N; x++) {
                Stack<Integer> st = new Stack<>();
                for (int y = 0; y < N; y++) {
                    if (board[y][x] == 1) st.push(y);
                    else if (board[y][x] == 2 && !st.isEmpty()){
                        count++;
                        while (!st.isEmpty()) st.pop();
                    }
                }
            }
            System.out.println("#" + test_case + " " + count);
        }
	}
}