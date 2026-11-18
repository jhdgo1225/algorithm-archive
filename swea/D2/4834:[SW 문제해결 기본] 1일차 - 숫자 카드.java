/*
 * [문제] SWEA 4834 [SW 문제해결 기본] 1일차 - 숫자 카드
 * [유형] 카운팅 배열
 * [핵심 아이디어]
 *   1. 카드의 숫자가 0~9 이므로 길이 10 배열에 등장 횟수를 센다.
 *   2. 0~9 를 순회하며 '>=' 로 비교하면 횟수가 같을 때 더 큰 숫자가 선택된다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 동점 시 큰 값을 고르려면 오름차순 순회 + '>=' 비교, 작은 값을 고르려면 '>' 비교.
 *   - 공백 없이 붙어서 입력되는 숫자열은 next() 로 문자열을 받아 charAt(i) - '0' 으로 변환한다.
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
            String s = sc.next();
            int[] nums = new int[10];
            for (int i = 0; i < N; i++) nums[s.charAt(i) - '0']++;

            int result = 0;
            for (int i = 0; i < 10; i++) {
                if (nums[i] >= nums[result]) result = i;
            }
            System.out.println("#" + test_case + " " + result + " " + nums[result]);
		}
	}
}
