/*
 * [문제] SWEA 1859 백만 장자 프로젝트
 * [유형] 그리디 (뒤에서부터 탐색)
 * [핵심 아이디어]
 *   1. 뒤에서 앞으로 가며 지금까지의 최대 매매가 maxNum 을 유지한다.
 *   2. 현재 가격이 maxNum 보다 낮으면 사 두었다가 maxNum 에 판다(스택에 쌓아 두고 한꺼번에 정산).
 *   3. 현재 가격이 maxNum 보다 높으면 쌓인 것을 정산하고 maxNum 을 갱신한다.
 *   4. (스택 없이 result += maxNum - arr[i] 만으로도 동일하게 풀린다.)
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - '미래의 최댓값을 알아야 하는 문제'는 뒤에서부터 보는 것이 정석이다.
 *   - 이익 합은 int 범위를 넘을 수 있으므로 반드시 long 으로 누적한다(N 최대 100만).
 *   - 정산하지 않은 항목이 스택에 남는 마지막 처리를 빠뜨리지 않는다.
 */

import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            for (int i=0; i<N; i++) arr[i] = sc.nextInt();
            Stack<Integer> st = new Stack<>();
            int maxNum = arr[N-1];
            long result = 0;
            for (int i=N-1; i>=0; i--) {
                if (maxNum < arr[i]) {
                    while (!st.isEmpty())
                        result += ((long)maxNum - st.pop());
                    maxNum = arr[i];
                }
                st.add(arr[i]);
            }
            while (!st.isEmpty())
                result += (maxNum - st.pop());
            System.out.println("#" + tc + " " + result);
        }
	}
}