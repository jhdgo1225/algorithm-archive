/*
 * [문제] SWEA 1946 간단한 압축 풀기
 * [유형] 구현 / 문자열 (Run-Length 복원)
 * [핵심 아이디어]
 *   1. (문자, 반복 횟수) 쌍을 입력 순서대로 풀어서 출력한다.
 *   2. 출력한 글자 수가 10의 배수가 될 때마다 줄을 바꾼다.
 * [시간복잡도] O(전체 글자 수)
 * [코테 가이드]
 *   - '한 줄에 K개씩 출력' 형식은 index % K == 0 일 때 개행하는 패턴이다.
 *   - 출력이 많으면 StringBuilder 로 모아서 한 번에 출력하는 것이 안전하다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case=1; test_case<=T; test_case++) {
            int N = sc.nextInt();
            String[] alphas = new String[N];
            int[] cnt = new int[N];
            int total = 0;
            for (int i=0; i<N; i++) {
                alphas[i] = sc.next();
                cnt[i] = sc.nextInt();
                total += cnt[i];
            }
            System.out.print("#" + test_case);
            int idx = 0, nextAlpha = 0;
            while (idx < total) {
                if (idx % 10 == 0) System.out.println();
                if (cnt[nextAlpha]-- > 0)
                    System.out.print(alphas[nextAlpha]);
                if (cnt[nextAlpha] == 0) nextAlpha++;
                idx++;
            }
            System.out.println();
        }
    }
}