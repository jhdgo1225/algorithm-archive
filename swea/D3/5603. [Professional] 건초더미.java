/*
 * [문제] SWEA 5603 [Professional] 건초더미
 * [유형] 수학 / 그리디
 * [핵심 아이디어]
 *   1. 모든 더미를 같은 높이로 만들어야 하므로 목표 높이는 평균값이다.
 *   2. 평균보다 낮은 더미가 채워야 하는 양의 합이 곧 옮겨야 하는 횟수다.
 * [시간복잡도] O(N log N) (정렬 없이 O(N) 가능)
 * [코테 가이드]
 *   - 하나를 옮기면 높은 쪽 -1, 낮은 쪽 +1 이므로 '평균보다 낮은 차이의 합'만 세면 된다.
 *   - 정렬은 필요 없다. 모든 원소에 대해 avg - h 가 양수일 때만 더하면 된다.
 *   - 평균이 정수로 나누어떨어지는지 문제 조건을 확인한다.
 */

import java.util.Scanner;
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
            int N = sc.nextInt(), idx = 0, avg, result = 0;
            int[] nums = new int[N];
            int sum = 0;
            for (int i=0; i<N; i++) {
                nums[i] = sc.nextInt();
                sum += nums[i];
            }
            Arrays.sort(nums);
            avg = sum / N;
            while (nums[idx] < avg) result += (avg - nums[idx++]);
            System.out.println("#" + test_case + " " + result);
		}
	}
}