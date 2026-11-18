/*
 * [문제] SWEA 5097 [SW 문제해결 기본] 6일차 - 회전
 * [유형] 수학 / 큐 회전
 * [핵심 아이디어]
 *   1. 큐의 맨 앞을 뒤로 보내는 작업을 M 번 하면 맨 앞은 원래 배열의 M % N 번째 원소가 된다.
 *   2. 시뮬레이션 없이 arr[M % N] 을 출력한다.
 * [시간복잡도] O(1)
 * [코테 가이드]
 *   - 원형(회전) 구조는 인덱스를 % N 으로 처리하면 시뮬레이션이 필요 없다.
 *   - 출력에 쓰이지 않는 원소도 끝까지 읽어야 다음 테스트케이스 입력이 밀리지 않는다.
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
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
            System.out.println("#" + test_case + " " + arr[M % N]);
		}
	}
}
