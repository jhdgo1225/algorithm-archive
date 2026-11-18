/*
 * [문제] SWEA 1213 [SW 문제해결 기본] 3일차 - String
 * [유형] 문자열 / 패턴 매칭
 * [핵심 아이디어]
 *   1. 찾을 문자열로 정규식 Pattern 을 만든다.
 *   2. Matcher.find() 를 반복 호출해 등장 횟수를 센다.
 * [시간복잡도] O(|문장| * |패턴|)
 * [코테 가이드]
 *   - find() 는 겹치지 않게 찾는다. 겹치는 등장도 세야 하면 indexOf(target, idx + 1) 반복을 쓴다.
 *   - 패턴에 . * + 같은 특수문자가 들어올 수 있으면 Pattern.quote(target) 로 감싼다.
 *   - 정규식 없이 indexOf 반복이나 직접 비교 루프로도 충분히 풀린다.
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=10;

		while (T-- > 0) {
            int testCase = sc.nextInt();
            String target = sc.next();
            String str = sc.next();
            Pattern pattern = Pattern.compile(target);
            Matcher matcher = pattern.matcher(str);
            int count = 0;
            while (matcher.find()) count++;
            System.out.println("#" + testCase + " " + count);
        }
	}
}