/*
 * [문제] SWEA 1970 쉬운 거스름돈
 * [유형] 그리디
 * [핵심 아이디어]
 *   1. 큰 화폐 단위(50000, 10000, ... 10)부터 가능한 만큼 나눠 개수를 센다.
 *   2. 나머지(N %= 단위)로 다음 단위를 처리한다.
 * [시간복잡도] O(화폐 종류 수)
 * [코테 가이드]
 *   - 화폐 단위가 서로 배수 관계일 때만 그리디가 최적이다.
 *   - 배수 관계가 아니면 DP(동전 교환 문제)로 풀어야 한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
        for (int test_case=1; test_case<=T; test_case++) {
            int N = sc.nextInt();
            int[] cnt = new int[8];
            for (int i=0; i<money.length; i++) {
                if (money[i] <= N) {
                    cnt[i] = N / money[i];
                    N %= money[i];
                }
            }
            System.out.println("#" + test_case);
            for (int c : cnt) System.out.print(c + " ");
            System.out.println();
        }
	}
}