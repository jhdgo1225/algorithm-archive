/*
 * [문제] SWEA 1221 [SW 문제해결 기본] 5일차 - GNS
 * [유형] 정렬 / 문자열
 * [핵심 아이디어]
 *   1. "ZRO" ~ "NIN" 을 0 ~ 9 순서로 배열에 둔다.
 *   2. 각 단어의 인덱스를 기준으로 비교하는 Comparator 로 정렬한다.
 * [시간복잡도] O(N log N)
 * [코테 가이드]
 *   - 값의 종류가 10 개뿐이므로 단어별 개수를 세는 카운팅 정렬로 O(N) 에 풀 수 있다.
 *   - Comparator 안에서 indexOf 를 부르면 비교마다 탐색이 일어난다. HashMap 으로 숫자를 미리 매핑하면 낫다.
 *   - N 이 최대 10000 이므로 출력은 StringBuilder 로 모아서 한 번에 한다.
 */

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String tc = sc.next();
            int N = sc.nextInt();
            String[] numSystem = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
            String[] nums = new String[N];
            for (int i=0; i<N; i++) {
                nums[i] = sc.next();
            }
            List<String> order = Arrays.asList(numSystem);
            Arrays.sort(nums, (num1, num2) -> order.indexOf(num1) - order.indexOf(num2));
            System.out.println(tc);
            for (String num : nums) System.out.print(num + " ");
            System.out.println();
        }
	}
}