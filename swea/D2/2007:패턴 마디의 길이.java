/*
 * [문제] SWEA 2007 패턴 마디의 길이
 * [유형] 문자열 / 반복 패턴
 * [핵심 아이디어]
 *   1. 마디 길이 len 을 1부터 10까지 늘려 가며 앞의 len 글자와 그다음 len 글자가 같은지 비교한다.
 *   2. 처음으로 같아지는 len 이 정답(가장 짧은 마디)이다.
 * [시간복잡도] O(10 * 10)
 * [코테 가이드]
 *   - '가장 짧은 반복 단위'는 짧은 길이부터 검사해 처음 성공한 길이를 답으로 한다.
 *   - 일반 문자열에서는 KMP 의 실패 함수(pi 배열)로 최소 반복 단위를 구할 수 있다.
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
		sc.nextLine();

		for (int tc=1; tc<=T; tc++) {
            String str = sc.nextLine();
            System.out.print("#" + tc + " ");
            for (int len=1; len<=10; len++) {
                if (str.substring(0, len).equals(str.substring(len, len*2))) {
                    System.out.println(len);
                    break;
                }
            }
        }
	}
}