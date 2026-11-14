/*
 * [문제] SWEA 1204 [SW 문제해결 기본] 1일차 - 최빈수 구하기
 * [유형] 구현 / 카운팅 배열
 * [핵심 아이디어]
 *   1. 점수 범위가 0~100으로 작으므로 크기 101 배열에 등장 횟수를 센다.
 *   2. 0~100을 순회하며 최대 빈도를 갱신한다. 동점이면 더 큰 점수를 답으로 하므로 '<=' 로 비교한다.
 * [시간복잡도] O(입력 1000개 + 점수 범위 101)
 * [코테 가이드]
 *   - 값의 범위가 작으면 HashMap 대신 배열 카운팅이 가장 빠르고 단순하다.
 *   - 동점 처리 규칙(작은 값/큰 값)을 문제에서 먼저 확인하고 비교 연산자(< / <=)로 반영한다.
 *   - 첫 입력(테스트 번호)처럼 쓰지 않는 값도 반드시 읽어서 버려야 입력이 밀리지 않는다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            sc.nextInt();
            int[] scoreDistribution = new int[101];
            for (int i=0; i<1000; i++) scoreDistribution[sc.nextInt()]++;
            int tmp=Integer.MIN_VALUE, result=Integer.MIN_VALUE;
            for (int i=0; i<=100; i++) {
                if (tmp <= scoreDistribution[i]) {
                    tmp = scoreDistribution[i];
                    result = i;
                }
            }
            System.out.println("#" + test_case + " " + result);
		}
	}
}