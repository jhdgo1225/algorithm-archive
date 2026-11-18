/*
 * [문제] SWEA 4873 [SW 문제해결 기본] 4일차 - 반복문자 지우기
 * [유형] 스택
 * [핵심 아이디어]
 *   1. 문자열을 앞에서부터 보며 스택의 top 과 같은 문자가 오면 pop(연속된 중복 제거)한다.
 *   2. 다르면 push 한다. 최종 스택 크기가 남은 글자 수다.
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - '바로 앞과 같으면 제거'는 스택의 대표 유형이다(인접 중복 제거, 괄호 짝 등).
 *   - 문자열을 매번 잘라 붙이면 O(L^2) 이 되므로 ArrayDeque 나 StringBuilder 를 스택처럼 쓴다.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            String string = sc.next();
            Deque<Character> st = new ArrayDeque<>();
            for (char ch : string.toCharArray()) {
                if (!st.isEmpty() && st.peek() == ch) {
                    st.pop();
                    continue;
                }
                st.push(ch);
            }
            System.out.println("#" + test_case + " " + st.size());
		}
	}
}
