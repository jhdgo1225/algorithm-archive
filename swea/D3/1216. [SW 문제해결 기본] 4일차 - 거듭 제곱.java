/*
 * [문제] SWEA 1217 [SW 문제해결 기본] 4일차 - 거듭 제곱
 * [유형] 재귀
 * [핵심 아이디어]
 *   1. N^M = N * N^(M-1) 로 정의한다.
 *   2. 종료 조건은 M == 1 일 때 N 을 반환하는 것이다.
 * [시간복잡도] O(M)
 * [코테 가이드]
 *   - 재귀 문제는 종료 조건과 점화식 두 줄을 먼저 적고 시작한다.
 *   - 분할 정복(N^M = (N^(M/2))^2)을 쓰면 O(log M) 이 되어 큰 지수에도 쓸 수 있다.
 *   - 결과가 int 범위를 넘을 수 있으면 long 이나 모듈러 연산을 쓴다.
 */

import java.util.Scanner;

class Solution
{
    public static int recursion(int N, int M) {
        if (M == 1) return N;
        return N * recursion(N, M - 1);
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=10;

		while (T-- > 0)
        {
            int tc = sc.nextInt();
            int N = sc.nextInt(), M = sc.nextInt();
            System.out.println("#" + tc + " " + Solution.recursion(N, M));
		}
	}
}