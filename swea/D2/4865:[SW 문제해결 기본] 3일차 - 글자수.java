/*
 * [문제] SWEA 4865 [SW 문제해결 기본] 3일차 - 글자수
 * [유형] 해시 / 빈도수 세기
 * [핵심 아이디어]
 *   1. str2 의 글자별 개수를 HashMap 으로 센다.
 *   2. str1 의 각 글자에 대해 str2 에서의 개수를 구하고 그중 최댓값을 출력한다.
 * [시간복잡도] O(len(str1) + len(str2))
 * [코테 가이드]
 *   - 빈도수는 HashMap.merge(key, 1, Integer::sum) 로 세고, 조회는 getOrDefault(key, 0) 를 쓴다.
 *   - 알파벳 대문자만 나온다면 int[26] 배열 카운팅이 더 빠르다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++)
		{
            String str1 = br.readLine();
            String str2 = br.readLine();

            Map<Character, Integer> letters = new HashMap<>();
            for (char ch : str2.toCharArray()) letters.merge(ch, 1, Integer::sum);

            int result = 0;
            for (char ch : str1.toCharArray()) {
                result = Math.max(result, letters.getOrDefault(ch, 0));
            }
            System.out.println("#" + test_case + " " + result);
		}
	}
}
