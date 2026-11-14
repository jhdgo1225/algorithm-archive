/*
 * [문제] SWEA 1940 가랏! RC카!
 * [유형] 시뮬레이션
 * [핵심 아이디어]
 *   1. 명령 0: 속도 유지, 1: 가속, 2: 감속(현재 속도보다 크게 감속하면 0).
 *   2. 매 초 명령을 처리한 뒤 현재 속도 v 를 총 이동 거리에 더한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 가속도 갱신 후 이동 거리를 더하는 순서를 문제 정의대로 지킨다.
 *   - 감속 시 속도가 음수가 되지 않도록 0 으로 보정하는 경계 조건을 먼저 처리한다.
 *   - 명령 0 은 추가 입력이 없다는 점(입력 형식 차이)을 주의한다.
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
            int result = 0, v = 0;
            for (int i=0; i<N; i++) {
                int C = sc.nextInt();
                if (C == 1 || C == 2) {
                    int dv = sc.nextInt();
                    if (C == 1) v += dv;
                    else if (C == 2) {
                        if (v < dv) v = 0;
                        else v -= dv;
                    }
                }
                result += v;
            }
            System.out.println("#" + test_case + " " + result);
        }
	}
}