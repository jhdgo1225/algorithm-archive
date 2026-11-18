/*
 * [문제] SWEA 5174 [SW 문제해결 기본] 8일차 - subtree
 * [유형] 트리 / 재귀 순회
 * [핵심 아이디어]
 *   1. 부모-자식 쌍을 읽어 nodes[부모] = {왼쪽 자식, 오른쪽 자식} 으로 저장한다(자식이 없으면 0).
 *   2. N 번 노드를 루트로 재귀하며 자기 자신 1 + 왼쪽 서브트리 크기 + 오른쪽 서브트리 크기를 반환한다.
 * [시간복잡도] O(E)
 * [코테 가이드]
 *   - 서브트리 크기는 size(node) = 1 + size(left) + size(right) 의 기본 재귀 패턴이다.
 *   - Java 는 Python 처럼 재귀 제한을 따로 올릴 필요는 없지만, 아주 깊으면 StackOverflowError 에 주의한다.
 *   - 자식 번호가 0 이면 '없음'으로 취급하는 약속을 이용한다.
 *   - 출력이 많으면 StringBuilder 에 모아 한 번에 출력한다.
 */

import java.util.Scanner;

class Solution
{
    static int[][] nodes;

    static int traversal(int parent) {
        int cnt = 1;
        if (nodes[parent][0] > 0) cnt += traversal(nodes[parent][0]);
        if (nodes[parent][1] > 0) cnt += traversal(nodes[parent][1]);
        return cnt;
    }

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++)
		{
            int E = sc.nextInt(), N = sc.nextInt();
            nodes = new int[E + 2][2];
            for (int i = 0; i < E; i++) {
                int parent = sc.nextInt(), child = sc.nextInt();
                if (nodes[parent][0] == 0) nodes[parent][0] = child;
                else nodes[parent][1] = child;
            }
            sb.append("#").append(test_case).append(" ").append(traversal(N)).append("\n");
		}
        System.out.print(sb);
	}
}
