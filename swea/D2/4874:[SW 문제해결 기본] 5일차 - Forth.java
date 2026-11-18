/*
 * [문제] SWEA 4874 [SW 문제해결 기본] 5일차 - Forth
 * [유형] 스택 / 후위 표기식 계산
 * [핵심 아이디어]
 *   1. 숫자는 스택에 push, 연산자를 만나면 두 수를 pop 해 계산한 결과를 다시 push 한다.
 *   2. 먼저 pop 한 값이 오른쪽 피연산자(num2), 나중에 pop 한 값이 왼쪽(num1)이다.
 *   3. 스택에 피연산자가 2개 미만인데 연산자가 나오거나 끝났을 때 스택 크기가 1 이 아니면 'error'.
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - Java 의 정수 나눗셈은 0 방향으로 버림(truncate)이라 문제 규칙과 그대로 일치한다.
 *   - 후위 표기식의 핵심 오류 조건(피연산자 부족, 결과 개수 != 1)을 외워 둔다.
 *   - 마지막 토큰 '.' 은 숫자도 연산자도 아니므로 자연스럽게 무시된다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Solution
{
    static boolean isNumber(String token) {
        for (char ch : token.toCharArray()) {
            if (!Character.isDigit(ch)) return false;
        }
        return !token.isEmpty();
    }

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++)
		{
            String[] tokens = br.readLine().trim().split("\\s+");
            Deque<Integer> st = new ArrayDeque<>();
            boolean flag = true;

            for (String token : tokens) {
                if (isNumber(token)) {
                    st.push(Integer.parseInt(token));
                } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                    if (st.size() < 2) {
                        flag = false;
                        break;
                    }
                    int num2 = st.pop();
                    int num1 = st.pop();
                    switch (token) {
                        case "+": st.push(num1 + num2); break;
                        case "-": st.push(num1 - num2); break;
                        case "*": st.push(num1 * num2); break;
                        case "/": st.push(num1 / num2); break;
                    }
                }
            }

            if (flag && st.size() == 1) System.out.println("#" + test_case + " " + st.pop());
            else System.out.println("#" + test_case + " error");
		}
	}
}
