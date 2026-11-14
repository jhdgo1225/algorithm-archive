/*
 * [문제] SWEA 2005 파스칼의 삼각형
 * [유형] 수학 / 조합 (파스칼의 삼각형)
 * [핵심 아이디어]
 *   1. i 번째 줄의 j 번째 수는 조합 C(i, j) 이다.
 *   2. C(n, r) 은 r 을 min(r, n-r) 로 줄이고 분자(n, n-1, ...)와 분모(r, r-1, ...)를 곱해 나눈다.
 * [시간복잡도] O(N^3) 수준 (N 이 매우 작음)
 * [코테 가이드]
 *   - 더 쉬운 방법은 DP: dp[i][j] = dp[i-1][j-1] + dp[i-1][j] (덧셈만 사용하므로 오버플로 위험이 작다).
 *   - 조합 곱셈은 중간값이 커져 int 오버플로가 날 수 있으니 N 범위를 확인한다.
 */

import java.util.Scanner;

public class Solution {
    private static int combination(int n, int r) {
        if (r == 0) return 1;
        if (r > n / 2) r = n - r;
        int a = 1, b = 1;
        while (r > 0) {
            a *= n--;
            b *= r--;
        }
        return a / b;
    }
    
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            System.out.println("#" + tc);
            for (int i=0; i<N; i++) {
                for (int j=i; j>=0; j--)
                    System.out.print(combination(i, j) + " ");
                System.out.println();
            }
        }
    }
}