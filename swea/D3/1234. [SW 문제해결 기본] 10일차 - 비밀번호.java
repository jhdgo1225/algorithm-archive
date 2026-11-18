/*
 * [문제] SWEA 1234 [SW 문제해결 기본] 10일차 - 비밀번호
 * [유형] 스택
 * [핵심 아이디어]
 *   1. 문자열을 앞에서부터 보며 스택의 top 과 같은 문자가 오면 pop 한다.
 *   2. 다르면 push 한다.
 *   3. 남은 스택을 아래부터 이어 붙인 것이 비밀번호다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - '인접한 같은 문자 쌍 제거'는 스택의 대표 유형이다. 제거 후 새로 붙는 쌍도 자동으로 처리된다.
 *   - java.util.Stack 을 for-each 로 돌면 아래(먼저 넣은 것)부터 순회된다.
 */

import java.util.Scanner;
import java.util.Stack;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            String str = sc.next();

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < N; i++) {
                char c = str.charAt(i);
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : stack) sb.append(c);

            System.out.println("#" + test_case + " " + sb);
        }
	}
}