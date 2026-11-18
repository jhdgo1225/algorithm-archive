/*
 * [문제] SWEA 1208 [SW 문제해결 기본] 1일차 - Flatten
 * [유형] 시뮬레이션 / 그리디
 * [핵심 아이디어]
 *   1. 덤프 횟수만큼 반복하며 가장 높은 칸에서 하나를 빼 가장 낮은 칸에 더한다.
 *   2. 덤프가 끝나면 다시 최고점과 최저점을 구해 차이를 출력한다.
 * [시간복잡도] O(덤프 횟수 * 100)
 * [코테 가이드]
 *   - 매번 최고/최저를 다시 찾는 게 핵심이다. 덤프 후 최고/최저 위치가 바뀔 수 있다.
 *   - 높이 범위가 1~100 으로 작으므로 높이별 개수(카운팅 배열)로 관리하면 더 빠르다.
 *   - 차이가 1 이하가 되면 더 덤프해도 변하지 않으므로 일찍 멈출 수 있다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = sc.nextInt();
            int[] nums = new int[100];
            for(int i = 0; i < 100; i++) nums[i] = sc.nextInt();
            int minIdx, maxIdx;
            while (N-- > 0) {
                minIdx = 0;
                maxIdx = 0;
                for (int i=0; i<100; i++) {
                    if (nums[i] < nums[minIdx]) minIdx = i;
                    if (nums[i] > nums[maxIdx]) maxIdx = i;
                }
                nums[minIdx]++;
                nums[maxIdx]--;
            }
            minIdx = 0;
            maxIdx = 0;
            for (int i=0; i<100; i++) {
                if (nums[i] < nums[minIdx]) minIdx = i;
                if (nums[i] > nums[maxIdx]) maxIdx = i;
            }
            System.out.println("#" + test_case + " " + (nums[maxIdx] - nums[minIdx]));

		}
	}
}