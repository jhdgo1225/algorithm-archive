/*
 * [문제] SWEA 1288 새로운 불면증 치료법
 * [유형] 구현 / 시뮬레이션 + 방문 체크
 * [핵심 아이디어]
 *   1. N, 2N, 3N ... 을 차례로 부르며 각 숫자의 자릿수를 boolean[10] 에 기록한다.
 *   2. 0~9가 모두 기록된 순간의 배수(N*k)가 정답이다.
 * [시간복잡도] O(k * 자릿수) (k는 모든 숫자를 볼 때까지의 배수, 매우 작음)
 * [코테 가이드]
 *   - 숫자를 Integer.toString 으로 바꾸면 자릿수 순회가 쉽다. 나머지 연산(% 10)으로도 가능하다.
 *   - 방문 체크가 10칸이면 비트마스크(mask |= 1 << d, mask == (1 << 10) - 1)로 더 간결하게 쓸 수 있다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case=1; test_case<=T; test_case++) {
            int N = sc.nextInt();
            int idx = 1, cnt = 0, result = 1;
            boolean[] saw = new boolean[10];
            while (true) {
                result = N * idx++;
                String str = Integer.toString(result);
                for (int i=0; i<str.length(); i++)
                    saw[str.charAt(i) - '0'] = true;
                int chk = 0;
                for (int i=0; i<10; i++) if (saw[i]) chk++;
                if (chk == 10) break;
            }
            System.out.println("#" + test_case + " " + result);
        }
	}
}