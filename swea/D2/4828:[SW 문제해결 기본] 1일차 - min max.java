/*
 * [문제] SWEA 4828 [SW 문제해결 기본] 1일차 - min max
 * [유형] 배열 / 최댓값, 최솟값
 * [핵심 아이디어]
 *   1. 배열을 한 번 순회하며 max 와 min 을 동시에 갱신하고 max - min 을 출력한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 초기값은 Integer.MIN_VALUE / Integer.MAX_VALUE 를 쓴다. 0 으로 두면 음수 입력에서 틀린다.
 *   - Math.max / Math.min 으로 한 줄씩 갱신하면 깔끔하다.
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
            int N = sc.nextInt();
            int maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();
                maxNum = Math.max(maxNum, num);
                minNum = Math.min(minNum, num);
            }
            System.out.println("#" + test_case + " " + (maxNum - minNum));
		}
	}
}
