/*
 * [문제] SWEA 2930 힙
 * [유형] 우선순위 큐 / 최대 힙
 * [핵심 아이디어]
 *   1. PriorityQueue 에 Collections.reverseOrder() 를 주어 최대 힙으로 쓴다.
 *   2. 연산 1 은 삽입, 연산 2 는 최댓값을 꺼내고 비어 있으면 -1 을 기록한다.
 *   3. 테스트 케이스마다 힙과 결과 리스트를 비운다.
 * [시간복잡도] O(N log N)
 * [코테 가이드]
 *   - 자바 PriorityQueue 는 기본이 최소 힙이다. 최대 힙은 reverseOrder() 나 (a, b) -> b - a 로 만든다.
 *   - 연산 수가 많으므로 출력은 StringBuilder 로 모으고, 입력이 많으면 BufferedReader 를 쓴다.
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> li = new ArrayList<>();

        for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
            for (int i=0; i<N; i++) {
                if (sc.nextInt() == 1) pq.add(sc.nextInt());
                else li.add(pq.isEmpty() ? -1 : pq.poll());
            }
            System.out.print("#" + test_case + " ");
            for (int num : li) System.out.print(num + " ");
            System.out.println();
            pq.clear();
            li.clear();
		}
	}
}