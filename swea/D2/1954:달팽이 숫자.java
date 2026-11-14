/*
 * [문제] SWEA 1954 달팽이 숫자
 * [유형] 구현 / 달팽이(나선) 채우기
 * [핵심 아이디어]
 *   1. 첫 행을 1~N 으로 채운 뒤, 남은 변의 길이를 N-1, N-1, N-2, N-2 ... 로 줄이며 채운다.
 *   2. 재귀 한 번에 '세로 이동 N칸 + 가로 이동 N칸'을 처리하고 방향(아래/위, 왼쪽/오른쪽)을 번갈아 바꾼다.
 * [시간복잡도] O(N^2)
 * [코테 가이드]
 *   - 가장 범용적인 방법은 방향 배열(dy, dx)을 두고 '범위를 벗어나거나 이미 채워진 칸이면 방향 전환'하는 것이다.
 *   - 나선형 순회는 변의 길이가 N, N-1, N-1, N-2, N-2 ... 로 줄어드는 규칙을 기억해 둔다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	private static void recursion(int[][] board, int N, int num, int r, int c, int updown) {
        if (N == 0) return ;
        int ny = (updown == 0) ? 1 : -1;
        int nx = (updown == 0) ? -1 : 1;
        for (int i=0; i<N; i++) {
            r += ny;
            board[r][c] = ++num;
        }
        for (int i=0; i<N; i++) {
            c += nx;
            board[r][c] = ++num;
        }
        recursion(board, N-1, num, r, c, (updown == 0) ? 1 : 0);
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case=1; test_case<=T; test_case++) {
            int N = sc.nextInt();
            int[][] board = new int[N][N];
            for (int i=1; i<=N; i++)
                board[0][i-1] = i;
            recursion(board, N-1, N, 0, N-1, 0);
            System.out.println("#" + test_case);
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }
        }
    }
}