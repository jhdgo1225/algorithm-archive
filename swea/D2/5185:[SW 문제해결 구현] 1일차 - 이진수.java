/*
 * [문제] SWEA 5185 [SW 문제해결 구현] 1일차 - 이진수
 * [유형] 구현 / 진법 변환 (16진수 -> 2진수)
 * [핵심 아이디어]
 *   1. 16진수 각 자리를 정수로 바꾸고 상위 비트부터 4비트 (value >> bit) & 1 로 꺼내 이어 붙인다.
 *   2. 각 자리는 반드시 4자리 2진수로 표현해야 한다(앞의 0 포함).
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - 16진수 한 글자는 Character.digit(ch, 16) 또는 Integer.parseInt(s, 16) 으로 변환한다.
 *   - Integer.toBinaryString 은 앞자리 0 을 버리므로 자리수를 직접 맞춰야 한다.
 *   - 비트 연산 기본: (x >> k) & 1 은 k 번째 비트, x | (1 << k) 는 비트 켜기, x & ~(1 << k) 는 끄기.
 */

import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            sc.nextInt();
            String hex = sc.next();
            sb.append("#").append(test_case).append(" ");
            for (char ch : hex.toCharArray()) {
                int value = Character.digit(ch, 16);
                for (int bit = 3; bit >= 0; bit--) sb.append((value >> bit) & 1);
            }
            sb.append("\n");
		}
        System.out.print(sb);
	}
}
