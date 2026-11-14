/*
 * [문제] SWEA 1959 두 개의 숫자열
 * [유형] 완전 탐색 / 슬라이딩
 * [핵심 아이디어]
 *   1. 항상 긴 배열을 arr1, 짧은 배열을 arr2 로 맞춘다(N < M 이면 서로 바꿔 호출).
 *   2. 짧은 배열을 긴 배열 위에서 한 칸씩 밀며 대응하는 원소의 곱의 합을 구하고 최댓값을 갱신한다.
 * [시간복잡도] O((N - M + 1) * M)
 * [코테 가이드]
 *   - 두 입력 중 어느 쪽이 긴지 모를 때는 함수 인자 순서를 바꿔 한 번만 구현한다.
 *   - 값에 음수가 있을 수 있으므로 최댓값 초기값은 0 이 아니라 Integer.MIN_VALUE 로 둔다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    private static int solution(int size1, int size2, int[] arr1, int[] arr2) {
        int result = Integer.MIN_VALUE;
        for (int i=0; i<=size1 - size2; i++) {
            int tmp = 0;
            for (int j=0; j<size2; j++) tmp += (arr1[i + j] * arr2[j]);
            result = Math.max(result, tmp);
        }
        return result;
    }
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt(), M = sc.nextInt(), result;
            int[] a = new int[N];
            for (int i=0; i<N; i++) a[i] = sc.nextInt();
            int[] b = new int[M];
            for (int i=0; i<M; i++) b[i] = sc.nextInt();
            if (N < M) result = solution(M, N, b, a);
            else result = solution(N, M, a, b);
            System.out.println("#" + test_case + " " + result);
		}
	}
}