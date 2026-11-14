/*
 * [문제] SWEA 1926 간단한 369게임
 * [유형] 구현 / 문자열 처리
 * [핵심 아이디어]
 *   1. 1부터 N까지 각 숫자를 문자열로 바꿔 3, 6, 9의 개수를 센다.
 *   2. 개수가 0이면 숫자를 그대로, 1 이상이면 개수만큼 '-' 를 출력한다.
 * [시간복잡도] O(N log N) (자릿수 순회)
 * [코테 가이드]
 *   - 숫자의 각 자릿수는 문자열 변환 또는 % 10, / 10 반복으로 구한다.
 *   - 한 줄에 공백으로 구분해 출력하는 형식을 놓치지 않는다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    private static int threeSixNine(int N) {
        String str = Integer.toString(N);
        int cnt = 0;
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '3' || ch == '6' || ch == '9') cnt++;
        }
        return cnt;
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
        for (int i=1; i<=N; i++) {
            int clap = threeSixNine(i);
            if (clap > 0) {
                for (int j=0; j<clap; j++) System.out.print("-");
                System.out.print(" ");
            }
            else System.out.print(i + " ");
        }
	}
}