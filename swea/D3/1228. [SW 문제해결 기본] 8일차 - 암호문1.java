/*
 * [문제] SWEA 1228 [SW 문제해결 기본] 8일차 - 암호문1
 * [유형] 배열 / 시뮬레이션 (삽입)
 * [핵심 아이디어]
 *   1. 삽입 명령마다 loc 위치 뒤의 원소를 cnt 칸씩 뒤로 민다.
 *   2. 비워진 자리에 새 숫자들을 넣는다.
 *   3. 배열 길이를 N 으로 고정해 넘치는 뒤쪽 원소는 버린다.
 * [시간복잡도] O(M * N)
 * [코테 가이드]
 *   - 앞 10 개만 출력하므로 뒤로 밀려 사라지는 원소는 신경 쓰지 않아도 된다.
 *   - 뒤로 밀 때는 뒤에서부터 복사해야 값이 덮어써지지 않는다.
 *   - ArrayList.addAll(loc, list) 를 쓰면 삽입을 한 줄로 처리할 수 있다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=10;

		for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[] arr = new int[N];
            for (int i=0; i<N; i++) arr[i] = sc.nextInt();
            int M = sc.nextInt();
            for (int i=0; i<M; i++) {
                sc.next();
                int loc = sc.nextInt();
                int cnt = sc.nextInt();
                int[] tmpArr = new int[cnt];
                for (int j=0; j<cnt; j++) tmpArr[j] = sc.nextInt();
                if (loc >= N) continue;
                for (int j=N-1; j>=loc+cnt; j--)
                    arr[j] = arr[j-cnt];
                for (int j=loc; j<loc+cnt && j < N; j++)
                    arr[j] = tmpArr[j - loc];
            }
            System.out.print("#" + test_case + " ");
            for (int i=0; i<10; i++) System.out.print(arr[i] + " ");
            System.out.println();
        }
	}
}