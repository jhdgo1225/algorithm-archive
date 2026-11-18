/*
 * [문제] SWEA 4835 [SW 문제해결 기본] 1일차 - 구간합
 * [유형] 슬라이딩 윈도우
 * [핵심 아이디어]
 *   1. 처음 M개의 합을 구한 뒤, 윈도우를 한 칸씩 이동하며 새 원소를 더하고 빠지는 원소를 뺀다.
 *   2. 그동안의 최대 합과 최소 합의 차이를 출력한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 고정 길이 구간합은 매번 다시 합하지 말고 '더하고 빼기'로 갱신한다.
 *   - 윈도우 이동 횟수는 N - M 회임을 헷갈리지 않는다.
 */

import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt(), M = sc.nextInt();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++) nums[i] = sc.nextInt();

            // 첫 번째 구간의 합
            int tmp = 0;
            for (int i = 0; i < M; i++) tmp += nums[i];
            int maxSum = tmp, minSum = tmp;

            // 슬라이딩 윈도우
            for (int i = 0; i < N - M; i++) {
                tmp += nums[i + M] - nums[i];
                maxSum = Math.max(maxSum, tmp);
                minSum = Math.min(minSum, tmp);
            }
            System.out.println("#" + test_case + " " + (maxSum - minSum));
		}
	}
}
