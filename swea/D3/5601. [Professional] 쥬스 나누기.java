/*
 * [문제] SWEA 5601 [Professional] 쥬스 나누기
 * [유형] 수학
 * [핵심 아이디어]
 *   1. N 명에게 똑같이 나누므로 각자 1/N 을 받는다.
 *   2. "1/N" 을 N 번 출력한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 출력 형식(분수 표기, 공백)을 예시와 정확히 맞추는 것이 전부인 문제다.
 *   - 반복 출력은 StringBuilder 로 모아서 출력하면 빠르다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            System.out.print("#" + test_case + " ");
            for (int i=0; i<N; i++) System.out.print(1 + "/" + N + " ");
            System.out.println();
        }
	}
}