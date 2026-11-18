/*
 * [문제] SWEA 5176 [SW 문제해결 기본] 8일차 - 이진탐색
 * [유형] 트리 / 중위 순회 (완전 이진 탐색 트리)
 * [핵심 아이디어]
 *   1. 완전 이진 트리를 배열(0-based, 자식 = 2i+1, 2i+2)로 표현한다.
 *   2. 이진 탐색 트리의 중위 순회 결과가 오름차순이므로 중위 순회하며 1 부터 N 까지 차례로 채운다.
 *   3. 루트(nodes[0])와 N / 2 번째 노드(nodes[N / 2 - 1])의 값을 출력한다.
 * [시간복잡도] O(N)
 * [코테 가이드]
 *   - 이진 탐색 트리 + 중위 순회 = 정렬된 순서. 이 성질로 값을 배치할 수 있다.
 *   - 완전 이진 트리의 배열 인덱스: 1-based 는 부모 i, 자식 2i, 2i+1 / 0-based 는 자식 2i+1, 2i+2.
 *   - 재귀 중 증가시키는 카운터(num)는 static 필드로 둔다.
 */

import java.util.Scanner;

class Solution
{
    static int[] nodes;
    static int num;

    static void traversal(int idx) {
        if (idx >= nodes.length) return;
        traversal(idx * 2 + 1);   // 왼쪽 자식
        nodes[idx] = num++;       // 현재 노드
        traversal(idx * 2 + 2);   // 오른쪽 자식
    }

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            nodes = new int[N];
            num = 1;
            traversal(0);
            sb.append("#").append(test_case).append(" ")
              .append(nodes[0]).append(" ").append(nodes[N / 2 - 1]).append("\n");
		}
        System.out.print(sb);
	}
}
