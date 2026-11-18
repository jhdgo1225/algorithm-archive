/*
 * [문제] SWEA 4864 [SW 문제해결 기본] 3일차 - 문자열 비교
 * [유형] 문자열 검색
 * [핵심 아이디어]
 *   1. 찾을 문자열이 대상 문자열 안에 포함되어 있으면 1, 아니면 0 을 출력한다.
 *   2. String.contains 로 부분 문자열 포함 여부를 확인한다.
 * [시간복잡도] O(N * M) (최악, 실제로는 매우 빠름)
 * [코테 가이드]
 *   - 직접 구현하면 이중 반복문, 최적화가 필요하면 KMP / 해시(라빈-카프)를 쓴다.
 *   - 코테에서는 라이브러리(contains, indexOf)를 먼저 고려한다.
 *   - 줄 단위 문자열 입력은 BufferedReader.readLine() 이 안전하다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++)
		{
            String needle = br.readLine().trim();
            String haystack = br.readLine().trim();
            int result = haystack.contains(needle) ? 1 : 0;
            System.out.println("#" + test_case + " " + result);
		}
	}
}
