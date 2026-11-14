/*
 * [문제] SWEA 1948 날짜 계산기
 * [유형] 구현 / 날짜 계산
 * [핵심 아이디어]
 *   1. 월별 일수 배열 {31,28,31,...} 을 준비한다.
 *   2. (첫 달 남은 일수 + 1) + (사이 달들의 일수 합) + (둘째 날 일) 로 두 날짜를 모두 포함한 일수를 구한다.
 * [시간복잡도] O(12)
 * [코테 가이드]
 *   - 날짜 문제는 '월별 일수 배열' + '누적합'(해당 날짜가 1월 1일부터 몇 번째 날인지)으로 푼다.
 *   - 양 끝 날짜를 포함하는지(+1) 여부를 문제에서 확인한다.
 *   - 같은 달인 경우(M1 == M2)는 D2 - D1 + 1 로 따로 처리해야 하는지 반드시 점검한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

		for (int test_case=1; test_case<=T; test_case++) {
            int M1 = sc.nextInt(), D1 = sc.nextInt(), M2 = sc.nextInt(), D2 = sc.nextInt();
            int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int result = (days[M1-1] - D1 + 1) + ((M1 == M2) ? 0 : D2);
            for (int i=M1+1; i < M2; i++) result += days[i-1];
            System.out.println("#" + test_case + " " + result);
        }
	}
}