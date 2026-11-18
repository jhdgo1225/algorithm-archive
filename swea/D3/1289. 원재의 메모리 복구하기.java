/*
 * [문제] SWEA 1289 원재의 메모리 복구하기
 * [유형] 그리디 / 비트마스크
 * [핵심 아이디어]
 *   1. 처음 메모리는 모두 0 이다.
 *   2. 왼쪽 비트부터 보며 현재 메모리 값과 원래 값이 다르면 그 위치부터 끝까지 원래 값으로 덮어쓴다.
 *   3. 덮어쓴 횟수가 답이다.
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - 결국 '이전 비트와 값이 바뀌는 지점의 수'(초기값 0 기준)를 세는 문제다. 문자열 비교만으로 풀린다.
 *   - 비트마스크로 풀 때 길이가 최대 50 이므로 int 가 아닌 long 과 1L << i 를 써야 한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
        sc.nextLine();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String str = sc.nextLine();
            int len = str.length() - 1;
            long originMem = Long.parseLong(str, 2);
            long initialMemory = 0;
            int result = 0;
            for (int i=len; i>=0; i--) {
                long mask = (long) 1 << i;
                if ((originMem & mask) != 0 && (initialMemory & mask) == 0) {
                    initialMemory |= (((long) 1 << (i + 1)) - 1);
                    result++;
                } else if ((originMem & mask) == 0 && (initialMemory & mask) != 0) {
                    initialMemory &= ~(((long) 1 << (i + 1)) - 1);
                    result++;
                }
            }
            System.out.println("#" + test_case + " " + result);
		}
	}
}