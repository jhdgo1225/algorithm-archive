/*
 * [문제] SWEA 4839 [SW 문제해결 기본] 2일차 - 이진탐색
 * [유형] 이진 탐색 (탐색 횟수 비교)
 * [핵심 아이디어]
 *   1. 문제에서 정의한 이진 탐색을 그대로 구현해 목표 페이지까지의 탐색 횟수를 센다.
 *   2. mid 보다 목표가 크면 s = mid, 작으면 e = mid 로 좁힌다(경계에서 무한 반복을 막기 위해 mid ± 1 보정).
 *   3. 탐색 횟수가 적은 사람이 승자이고, 같으면 0 을 출력한다.
 * [시간복잡도] O(log P)
 * [코테 가이드]
 *   - 문제가 표준 이진 탐색(mid ± 1)과 다른 규칙을 주면 그대로 구현해야 횟수가 맞는다.
 *   - 이진 탐색은 s <= e 반복, mid = (s + e) / 2 형태를 기본 틀로 암기한다.
 *   - 값이 크면 (s + e) 가 int 범위를 넘을 수 있으므로 s + (e - s) / 2 를 쓴다.
 */

import java.util.Scanner;

class Solution
{
    static int binarySearch(int s, int e, int target) {
        int cnt = 0;
        while (s <= e) {
            int mid = (s + e) / 2;
            cnt++;
            if (target == mid) break;
            else if (target > mid) s = (s == mid) ? mid + 1 : mid;
            else e = (e == mid) ? mid - 1 : mid;
        }
        return cnt;
    }

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int p = sc.nextInt(), p1 = sc.nextInt(), p2 = sc.nextInt();
            int p1Cnt = binarySearch(1, p, p1);
            int p2Cnt = binarySearch(1, p, p2);

            String result;
            if (p1Cnt < p2Cnt) result = "A";
            else if (p1Cnt > p2Cnt) result = "B";
            else result = "0";
            System.out.println("#" + test_case + " " + result);
		}
	}
}
