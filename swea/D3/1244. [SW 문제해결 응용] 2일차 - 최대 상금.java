/*
 * [문제] SWEA 1244 [SW 문제해결 응용] 2일차 - 최대 상금
 * [유형] 완전 탐색 / DFS + 메모이제이션
 * [핵심 아이디어]
 *   1. 남은 교환 횟수만큼 두 자리를 골라 바꾸는 모든 경우를 DFS 로 탐색한다.
 *   2. (교환 횟수, 현재 숫자) 상태를 visited 로 기록해 같은 상태를 다시 탐색하지 않는다.
 *   3. 교환을 모두 마친 숫자 중 최댓값을 출력한다.
 * [시간복잡도] O(교환 횟수 * 상태 수 * C(L, 2)) (L = 자릿수, 상태 수 <= L!)
 * [코테 가이드]
 *   - 같은 숫자도 남은 교환 횟수가 다르면 결과가 달라지므로 상태는 (depth, num) 쌍으로 잡는다.
 *   - 교환은 반드시 정확히 count 번 해야 한다. 이미 최대여도 남은 횟수를 소진해야 한다.
 *   - 교환 횟수가 자릿수 이상이면 '정렬된 최댓값'에 도달 가능하므로 횟수를 줄이는 최적화도 가능하다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static boolean[][] visited;
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static int toNum(int[] arr) {
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num = num * 10 + arr[i];
        }
        return num;
    }
    private static int dfs(int[] arr, int step, int maxStep) {
        int num = toNum(arr);
        if (step == maxStep) return num;
        if (visited[step][num]) return -1;
        visited[step][num] = true;

        int maxResult = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                swap(arr, i, j);
                maxResult = Math.max(maxResult, dfs(arr, step+1, maxStep));
                swap(arr, i, j);
            }
        }
        return maxResult;
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            String str = sc.next();
            int count = sc.nextInt();
            int[] arr = new int[str.length()];
            for (int i = 0; i < str.length(); i++)
                arr[i] = str.charAt(i) - '0';
            visited = new boolean[count+1][(int) Math.pow(10, arr.length)];
            int result = dfs(arr, 0, count);
            System.out.println("#" + test_case + " " + result);
		}
	}
}