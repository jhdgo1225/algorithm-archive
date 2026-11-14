/*
 * [문제] SWEA 21425 +=
 * [유형] 시뮬레이션 / 그리디
 * [핵심 아이디어]
 *   1. a, b 중 큰 쪽을 작은 쪽에 더한다(a > b 이면 b += a, 아니면 a += b).
 *   2. a 또는 b 가 n 을 초과하기 전까지 반복하며 횟수를 센다.
 *   3. 항상 작은 값에 큰 값을 더해야 값이 가장 빨리 커져 연산 횟수가 최소가 된다.
 * [시간복잡도] O(log N) (피보나치처럼 증가)
 * [코테 가이드]
 *   - 값이 피보나치처럼 증가하면 반복 횟수는 로그 수준이라 시뮬레이션으로 충분하다.
 *   - 조건은 a <= n && b <= n 이므로 둘 다 n 이하일 때만 계속하는지 확인한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		int a, b, n, cnt;

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			a = sc.nextInt();
			b = sc.nextInt();
			n = sc.nextInt();
			cnt = 0;
			while (a <= n && b <= n) {
				if (a > b) {
					b += a;
				} else {
					a += b;
				}
				cnt++;
			}
			System.out.println(cnt);
		}
	}
}