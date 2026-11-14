/*
 * [문제] SWEA 1285 아름이의 돌 던지기
 * [유형] 구현 / 최솟값 + 개수 세기
 * [핵심 아이디어]
 *   1. 각 돌의 위치에 절댓값을 취해 기준점과의 거리를 구한다.
 *   2. 더 가까운 거리가 나오면 minDist 를 갱신하고 cnt = 1 로 초기화한다.
 *   3. 같은 거리가 나오면 cnt++ 한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - '최솟값과 그 개수'는 한 번의 순회로 갱신(<)과 누적(==)을 나눠 처리하는 패턴이다.
 *   - 초기값은 문제의 최대 범위보다 크게 잡는다(여기서는 100000). Integer.MAX_VALUE 도 가능하다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int minDist = 100000;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				int num = sc.nextInt();
				int dist = Math.abs(num);
				if (minDist > dist) {
					minDist = dist;
					cnt = 1;
				} else if (minDist == dist) cnt++;
			}
			System.out.println("#" + test_case + " " + minDist + " " + cnt);
		}
	}
}
