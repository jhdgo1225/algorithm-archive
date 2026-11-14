/*
 * [문제] SWEA 1986 지그재그 숫자
 * [유형] 수학 / 규칙 찾기
 * [핵심 아이디어]
 *   1. 1 - 2 + 3 - 4 + ... 의 합을 반복 없이 공식으로 구한다.
 *   2. N 이 홀수면 (N + 1) / 2, N 이 짝수면 -N / 2 이다.
 * [시간복잡도] O(1)
 * [코테 가이드]
 *   - 반복문으로 풀 수 있어도 규칙을 찾으면 O(1) 이 된다. 작은 N 으로 직접 써 보며 규칙을 찾는다.
 *   - 부호가 번갈아 나오는 합은 쌍(2개씩) 묶어 보면 규칙이 보인다.
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
            int N = sc.nextInt();
            System.out.println("#" + test_case + " " + ((((N+1) % 2 == 1) ? -1 : 1) * ((N+1) / 2)));
		}
	}
}