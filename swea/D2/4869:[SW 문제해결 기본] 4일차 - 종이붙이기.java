/*
 * [문제] SWEA 4869 [SW 문제해결 기본] 4일차 - 종이붙이기
 * [유형] DP (타일링)
 * [핵심 아이디어]
 *   1. 가로 10 단위로 나눠 N / 10 = n 이라 하면 점화식 dp[n] = dp[n-1] + 2 * dp[n-2].
 *   2. dp[1] = 1, dp[2] = 3 으로 시작해 미리 30까지 계산해 두고 질의마다 조회한다.
 * [시간복잡도] O(1) per query (전처리 O(30))
 * [코테 가이드]
 *   - 타일링 DP 는 '마지막 칸을 어떻게 채우는가'로 경우를 나눠 점화식을 세운다.
 *   - 테스트케이스가 여러 개면 DP 표를 한 번만 만들어 재사용한다.
 *   - dp 값이 빠르게 커지므로 범위가 크면 long 을 쓴다(dp[30] 은 약 7억으로 int 범위 안).
 */

import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        long[] dp = new long[31];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= 30; i++) dp[i] = 2 * dp[i - 2] + dp[i - 1];

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            System.out.println("#" + test_case + " " + dp[N / 10]);
		}
	}
}
