/*
 * [문제] SWEA 1989 초심자의 회문 검사
 * [유형] 문자열 / 투 포인터
 * [핵심 아이디어]
 *   1. 양 끝에서 안쪽으로 오면서 str[i] 와 str[len-1-i] 를 비교한다.
 *   2. 다른 글자가 하나라도 있으면 0, 끝까지 같으면 1.
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - 절반(len / 2)까지만 비교하면 충분하다.
 *   - 정수를 읽은 뒤 문자열 줄을 읽을 때는 sc.nextLine() 으로 개행을 먼저 비워야 한다.
 *   - 짧게는 new StringBuilder(s).reverse().toString().equals(s) 로도 가능하다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        sc.nextLine();
        for (int tc=1; tc<=T; tc++) {
            String str = sc.nextLine();
            int len = str.length(), result = 1;
            for (int i=0; i<len/2; i++) {
                if (str.charAt(i) != str.charAt(len - i - 1)) {
                    result = 0;
                    break;
                }
            }
            System.out.println("#" + tc + " " + result);
        }
	}
}