/*
 * [문제] SWEA 1284 수도 요금 경쟁
 * [유형] 수학 / 단순 구현
 * [핵심 아이디어]
 *   1. A사 요금 = P * W.
 *   2. B사 요금 = W <= R 이면 Q, 아니면 Q + (W - R) * S.
 *   3. 두 값 중 작은 값을 출력한다.
 * [시간복잡도] O(1)
 * [코테 가이드]
 *   - 조건이 있는 요금식은 삼항 연산자로 식 그대로 옮기면 실수가 줄어든다.
 *   - '초과분에만 단가 적용'인지 '전체에 적용'인지 문제의 문구를 정확히 읽는다.
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

		for (int test_case=1; test_case<=T; test_case++) {
            int P = sc.nextInt();
            int Q = sc.nextInt();
            int R = sc.nextInt();
            int S = sc.nextInt();
            int W = sc.nextInt();
            int A = P * W;
            int B = (W <= R) ? Q : Q + (W - R) * S;
            System.out.println("#" + test_case + " " + ((A < B) ? A : B));
        }
	}
}