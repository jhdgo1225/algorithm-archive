/*
 * [문제] SWEA 1984 중간 평균값 구하기
 * [유형] 구현 / 최대 최소 제외 평균
 * [핵심 아이디어]
 *   1. 10개의 합과 최댓값, 최솟값을 한 번에 구한다.
 *   2. (합 - 최대 - 최소) / 8 을 소수점 첫째 자리에서 반올림(Math.round)한다.
 * [시간복잡도] O(10)
 * [코테 가이드]
 *   - 평균은 정수 나눗셈이 되지 않도록 float/double 로 계산한다.
 *   - 반올림은 Math.round, 올림 Math.ceil, 내림 Math.floor 를 구분해 사용한다.
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
            float max=Integer.MIN_VALUE, min=Integer.MAX_VALUE, sum = 0;
            for (int i=0; i<10; i++) {
                int tmp = sc.nextInt();
                sum += tmp;
                max = Math.max(max, tmp);
                min = Math.min(min, tmp);
            }
			System.out.println("#" + test_case + " " + Math.round((sum - max - min) / 8));
        }
	}
}