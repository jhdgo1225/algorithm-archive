/*
 * [문제] SWEA 1206 [SW 문제해결 기본] 1일차 - View
 * [유형] 배열 / 단순 구현
 * [핵심 아이디어]
 *   1. 양쪽 끝 2칸은 빈 땅이므로 인덱스 2 ~ N-3 의 빌딩만 본다.
 *   2. 빌딩 i 의 조망권 세대 수 = h[i] - max(좌우 2칸씩 총 4개 빌딩 높이).
 *   3. 그 값이 양수일 때만 누적한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - '양옆 거리 2 이내가 모두 비어 있어야 한다'는 조건은 이웃 4칸의 최댓값과의 차이로 바로 바뀐다.
 *   - min(h[i] - h[j]) 를 여러 번 구하는 것보다 h[i] - max(이웃) 한 번이 더 읽기 쉽다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] buildings = new int[N];
            int viewCount = 0;
            for (int i=0; i<N; i++) buildings[i] = sc.nextInt();
            for (int i=2; i<N-2; i++) {
                int availViewCount = buildings[i];
                availViewCount = Math.min(availViewCount, buildings[i] - buildings[i-1]);
                availViewCount = Math.min(availViewCount, buildings[i] - buildings[i-2]);
                availViewCount = Math.min(availViewCount, buildings[i] - buildings[i+1]);
                availViewCount = Math.min(availViewCount, buildings[i] - buildings[i+2]);
                if (availViewCount > 0) viewCount += availViewCount;
            }
            System.out.println("#" + tc + " " + viewCount);
        }
	}
}