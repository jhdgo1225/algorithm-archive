/*
 * [문제] SWEA 4880 [SW 문제해결 기본] 5일차 - 토너먼트 카드게임
 * [유형] 분할 정복 / 재귀 (토너먼트)
 * [핵심 아이디어]
 *   1. 구간 [s, e] 를 반으로 나눠 각 구간의 승자를 재귀로 구한 뒤 두 승자를 대결시킨다.
 *   2. 가위바위보 규칙: 1 이 2 에게, 2 가 3 에게, 3 이 1 에게 진다. 비기면 번호가 작은(앞) 사람이 이긴다.
 *   3. 구간 길이가 1 이면 그 번호를 반환한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 토너먼트/구간 문제는 mid = (s + e) / 2 로 나누는 분할 정복이 자연스럽다.
 *   - 비겼을 때 누가 이기는지(번호가 작은 쪽) 조건을 반드시 확인한다.
 *   - 출력은 0-based 인덱스에 +1 해야 한다.
 */

import java.util.Scanner;

class Solution
{
    static int[] cards;

    static int rsp(int p1, int p2) {
        if (cards[p1] == cards[p2]) return p1;
        if ((cards[p1] == 1 && cards[p2] == 2)
                || (cards[p1] == 2 && cards[p2] == 3)
                || (cards[p1] == 3 && cards[p2] == 1)) return p2;
        return p1;
    }

    static int play(int s, int e) {
        if (s == e) return s;
        int mid = (s + e) / 2;
        int p1 = play(s, mid);
        int p2 = play(mid + 1, e);
        return rsp(p1, p2);
    }

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            cards = new int[N];
            for (int i = 0; i < N; i++) cards[i] = sc.nextInt();
            System.out.println("#" + test_case + " " + (play(0, N - 1) + 1));
		}
	}
}
