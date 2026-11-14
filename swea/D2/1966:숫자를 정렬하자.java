/*
 * [문제] SWEA 1966 숫자를 정렬하자
 * [유형] 정렬
 * [핵심 아이디어]
 *   1. 입력을 배열에 받아 Arrays.sort 로 오름차순 정렬 후 출력한다.
 * [시간복잡도] O(N log N)
 * [코테 가이드]
 *   - 기본형 배열은 Arrays.sort, 객체/2차원 배열은 Comparator 를 넘겨 정렬한다.
 *   - N 이 작으면 어떤 정렬이든 통과하지만, 정렬 라이브러리 사용법은 반드시 익혀 둔다.
 */

import java.util.Scanner;
import java.util.Arrays;
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
            int[] a = new int[N];
            for (int i=0; i<N; i++) a[i] = sc.nextInt();
            Arrays.sort(a);
            System.out.print("#" + test_case + " ");
            for (int i=0; i<N; i++) System.out.print(a[i] + " ");
            System.out.println();
		}
	}
}