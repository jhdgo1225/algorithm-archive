/*
 * [문제] SWEA 5642 [Professional] 합
 * [유형] DP / 카데인 알고리즘 (최대 연속 부분합)
 * [핵심 아이디어]
 *   1. cur = i 번째 원소로 끝나는 최대 연속합 = max(a[i], cur + a[i]).
 *   2. 매 단계에서 cur 로 전체 최댓값을 갱신한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - '연속 부분 배열의 최대 합'은 카데인 알고리즘이 정석이다.
 *   - 모든 값이 음수일 수 있으므로 답의 초기값은 0 이 아닌 Integer.MIN_VALUE 나 첫 원소로 둔다.
 *   - 합이 int 범위를 넘을 수 있으면 long 을 쓴다.
 */

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
		    int N = sc.nextInt();
            int maxSum = Integer.MIN_VALUE;
            int cur = 0;
            
            for (int i=0; i<N; i++) {
                int tmp = sc.nextInt();
                cur = Math.max(tmp, cur + tmp);
                maxSum = Math.max(maxSum, cur);
            }
            System.out.println("#" + test_case + " " + maxSum);
		}
	}
}