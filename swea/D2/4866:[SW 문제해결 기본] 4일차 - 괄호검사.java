/*
 * [문제] SWEA 4866 [SW 문제해결 기본] 4일차 - 괄호검사
 * [유형] 스택 / 괄호 검사
 * [핵심 아이디어]
 *   1. 여는 괄호 '(' '{' 는 스택에 넣는다.
 *   2. 닫는 괄호가 나오면 스택이 비어 있지 않고 맨 위가 짝이 맞을 때만 pop 한다.
 *   3. 도중에 실패하거나 끝났을 때 스택이 비어 있지 않으면 0, 아니면 1.
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - 괄호 문제의 3대 실패 조건: 닫는 괄호가 먼저 나옴, 짝이 안 맞음, 끝났는데 스택에 남음.
 *   - Java 스택은 Stack 대신 ArrayDeque(push / pop / peek)를 쓰는 것이 권장된다.
 *   - 줄에 공백이 섞여 있을 수 있으므로 readLine() 으로 한 줄 전체를 읽는다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++)
		{
            String string = br.readLine();
            Deque<Character> st = new ArrayDeque<>();
            int result = 1;

            for (char ch : string.toCharArray()) {
                if (ch == '(' || ch == '{') {
                    st.push(ch);
                } else if (ch == ')' || ch == '}') {
                    if (st.isEmpty()) {
                        result = 0;
                        break;
                    }
                    if ((ch == ')' && st.peek() == '(') || (ch == '}' && st.peek() == '{')) {
                        st.pop();
                    } else {
                        result = 0;
                        break;
                    }
                }
            }
            if (!st.isEmpty()) result = 0;
            System.out.println("#" + test_case + " " + result);
		}
	}
}
