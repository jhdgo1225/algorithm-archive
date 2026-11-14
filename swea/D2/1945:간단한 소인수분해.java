/*
 * [문제] SWEA 1945 간단한 소인수분해
 * [유형] 수학 / 소인수분해
 * [핵심 아이디어]
 *   1. N 이 2, 3, 5, 7, 11 로만 이루어져 있으므로 각 소수로 나눌 수 있는 만큼 나누며 지수를 센다.
 * [시간복잡도] O(log N)
 * [코테 가이드]
 *   - 소인수분해 기본형: for p in 소수: while N % p == 0: N /= p; cnt++.
 *   - 일반 N 은 p * p <= N 까지만 검사하고 남은 N > 1 이면 그 자체가 소수다.
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
            int[] prime = {2, 3, 5, 7, 11};
            int[] cnt = new int[5];
            for (int i=0; i<5; i++) {
                while (N % prime[i] == 0) {
                    N /= prime[i];
                    cnt[i]++;
                }
            }
            System.out.print("#" + test_case + " ");
            for (int i=0; i<5; i++) System.out.print(cnt[i] + " ");
            System.out.println();
		}
	}
}