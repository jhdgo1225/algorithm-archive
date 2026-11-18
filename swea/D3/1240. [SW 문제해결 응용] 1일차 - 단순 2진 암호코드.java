/*
 * [문제] SWEA 1240 [SW 문제해결 응용] 1일차 - 단순 2진 암호코드
 * [유형] 문자열 / 구현
 * [핵심 아이디어]
 *   1. 0 만으로 된 줄이 아닌 줄을 하나 고른다.
 *   2. 암호코드는 항상 1 로 끝나므로 오른쪽에서 첫 번째 1 의 위치를 찾고, 그곳까지 56 비트를 잘라낸다.
 *   3. 7 비트씩 HashMap 으로 숫자로 바꾸고, (홀수 자리 합 * 3 + 짝수 자리 합) % 10 == 0 이면 숫자 합을, 아니면 0 을 출력한다.
 * [시간복잡도] O(N * M)
 * [코테 가이드]
 *   - 끝 위치를 기준으로 거꾸로 잘라내는 것이 핵심이다. 앞쪽 0 은 숫자 0 의 패턴(0001101)과 구분되지 않는다.
 *   - '홀수 자리'는 1 번째, 3 번째... 이므로 0-based 인덱스로는 짝수다. 문제 기준을 헷갈리지 않는다.
 *   - 코드 패턴 10 개는 static 배열이나 Map 으로 한 번만 만들어 둔다.
 */

import java.util.Scanner;
import java.util.HashMap;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("0001101", 0);
		map.put("0011001", 1);
		map.put("0010011", 2);
		map.put("0111101", 3);
		map.put("0100011", 4);
		map.put("0110001", 5);
		map.put("0101111", 6);
		map.put("0111011", 7);
		map.put("0110111", 8);
		map.put("0001011", 9);
        for (int test_case=1; test_case<=T; test_case++) {
			int N = sc.nextInt(), M = sc.nextInt();
			sc.nextLine();
			String[] cipherCodes = new String[N];
			String tmp = null;
            for (int i=0; i<N; i++) {
				String code = sc.nextLine();
				if (!code.matches("0+"))
					tmp = code;
			}
			int idx = tmp.length(), cnt = 0, odd = 0, even = 0;
			while (idx-- > 0)
				if (tmp.charAt(idx) == '1')
					break;
			for (int i=idx-55; i<=idx; i+=7) {
				int num = map.get(tmp.substring(i, i+7));
				if (cnt % 2 == 0) odd += num;
				else even += num;
				cnt++;
			}
			System.out.println("#" + test_case + " " + (((odd * 3 + even) % 10 != 0) ? 0 : odd + even));
        }
	}
}