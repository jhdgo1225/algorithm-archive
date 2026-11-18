/*
 * [문제] SWEA 5186 [SW 문제해결 구현] 1일차 - 이진수2
 * [유형] 구현 / 진법 변환 (소수 -> 2진수)
 * [핵심 아이디어]
 *   1. 2^-1, 2^-2, ... 값 b 를 차례로 만들며 a >= b 이면 1 을 쓰고 a -= b, 아니면 0 을 쓴다.
 *   2. a 가 0 이 되면 종료하고, 12자리를 넘겨도 a > 0 이면 'overflow' 를 출력한다.
 * [시간복잡도] O(13)
 * [코테 가이드]
 *   - 다른 방법: a *= 2 후 1 이상이면 '1' 을 쓰고 a -= 1, 아니면 '0' 을 쓰는 방식이 더 직관적이다.
 *   - 2 의 거듭제곱 분수는 double 로 정확히 표현되므로 소수 오차 걱정이 적다.
 *   - 자릿수 제한(12자리) 경계 조건을 문제에서 확인한다.
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
            double a = Double.parseDouble(sc.next());
            double b = 1;
            StringBuilder binary = new StringBuilder();
            while (a > 0 && binary.length() <= 12) {
                b /= 2;
                if (a >= b) {
                    a -= b;
                    binary.append('1');
                } else {
                    binary.append('0');
                }
            }
            System.out.println("#" + test_case + " " + (a > 0 ? "overflow" : binary.toString()));
		}
	}
}
