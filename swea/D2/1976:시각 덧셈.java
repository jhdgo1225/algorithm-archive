/*
 * [문제] SWEA 1976 시각 덧셈
 * [유형] 수학 / 시간 계산
 * [핵심 아이디어]
 *   1. 분은 (m1 + m2) % 60, 시는 h1 + h2 + (m1 + m2) / 60 에 12시간제 보정을 한다.
 *   2. 시를 % 12 한 결과가 0 이면 12 로 출력한다.
 * [시간복잡도] O(1)
 * [코테 가이드]
 *   - 12시간제는 % 12 후 0 을 12 로 바꾸는 보정이 항상 필요하다.
 *   - 분 -> 시 올림(carry)은 나눗셈과 나머지 두 연산으로 처리한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int h1 = sc.nextInt(), m1 = sc.nextInt(), h2 = sc.nextInt(), m2 = sc.nextInt();
            int h3 = (h1 + h2 + (m1 + m2) / 60) % 12;
            int m3 = (m1 + m2) % 60;
            System.out.println("#" + test_case + " " + ((h3 == 0) ? 12 : h3) + " " + m3);
		}
	}
}