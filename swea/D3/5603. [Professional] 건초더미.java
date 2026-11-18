/*
 * [문제] SWEA 5603 [Professional] 건초더미
 * [유형] 수학 / 그리디
 * [핵심 아이디어]
 *   1. 모든 더미를 같은 높이로 만들어야 하므로 목표 높이는 평균값이다.
 *   2. 처음부터 끝까지 순회하며 평균보다 낮은 더미는 그 차이만큼 옮겨 와야 한다.
 *   3. 그 차이들의 합이 곧 옮기는 횟수다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 하나를 옮기면 높은 쪽 -1, 낮은 쪽 +1 이므로 '평균보다 낮은 차이의 합'만 세면 된다.
 *   - 정렬은 필요 없다. 평균을 구한 뒤 한 번 순회하면 충분하다.
 *   - 평균이 정수로 나누어떨어지는지 문제 조건을 확인한다.
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
            int N = sc.nextInt(), avg, sum = 0, result = 0;
            int[] nums = new int[N];
            for (int i=0; i<N; i++) {
                nums[i] = sc.nextInt();
                sum += nums[i];
            }
            avg = sum / N;
            for (int i=0; i<nums.length; i++)
                if (nums[i] < avg)
                    result += (avg - nums[i]);
            System.out.println("#" + test_case + " " + result);
		}
	}
}