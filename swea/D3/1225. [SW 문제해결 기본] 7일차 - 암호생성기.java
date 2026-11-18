/*
 * [문제] SWEA 1225 [SW 문제해결 기본] 7일차 - 암호생성기
 * [유형] 큐 / 시뮬레이션
 * [핵심 아이디어]
 *   1. 8 개의 숫자를 큐에 넣는다.
 *   2. 맨 앞 숫자를 꺼내 1, 2, 3, 4, 5 를 차례로 빼고 맨 뒤에 넣는 것을 반복한다.
 *   3. 방금 넣은 숫자가 0 이하가 되면 0 으로 고정하고 종료한다.
 * [시간복잡도] O(반복 횟수) (값이 작아 충분히 빠름)
 * [코테 가이드]
 *   - '앞에서 꺼내 뒤로 넣기'는 큐(ArrayDeque) 회전 패턴이다.
 *   - 감소량 1~5 는 currentStep % 5 + 1 처럼 나머지 연산으로 순환시킬 수 있다.
 *   - 종료 시 마지막 값은 음수가 될 수 있으니 0 으로 맞춰 출력한다.
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

        while (T-- > 0)
        {
            int tc = sc.nextInt();
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i=0; i<8; i++) dq.addLast(sc.nextInt());
            int maxStep = 5, currentStep = 1;
            while (dq.peekLast() > 0) {
                dq.addLast(dq.pollFirst() - currentStep);
                currentStep++;
                if (currentStep > maxStep) currentStep = 1;
            }
            System.out.print("#" + tc + " ");
            for (int num : dq)
                System.out.print((num <= 0 ? 0 : num) + " ");
            System.out.println();
        }
	}
}