/*
 * [문제] SWEA 1983 조교의 성적 매기기
 * [유형] 정렬 / 구현
 * [핵심 아이디어]
 *   1. 총점 = 중간 * 0.35 + 기말 * 0.45 + 과제 * 0.2 로 계산한다.
 *   2. (총점, 원래 번호) 쌍으로 총점 내림차순 정렬한다.
 *   3. 등수 i 의 학점은 rating[i / (N / 10)] 이다(학점당 N/10 명).
 * [시간복잡도] O(N log N)
 * [코테 가이드]
 *   - 정렬하면 원래 위치를 잃으므로 인덱스를 함께 저장한다.
 *   - 내림차순 비교는 Double.compare(b, a) 로 쓰고 직접 뺄셈은 피한다.
 *   - 학점 구간은 등수 / 구간 크기의 정수 나눗셈으로 구한다.
 */

import java.util.Scanner;
import java.util.Arrays;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt(), K = sc.nextInt();
            double[][] scores = new double[N][2];
            String[] grades = new String[N];
            String[] rating = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
            for (int i=0; i<N; i++) {
                scores[i][0] = (sc.nextDouble() * 0.35 + sc.nextDouble() * 0.45 + sc.nextDouble() * 0.2);
                scores[i][1] = i;
            }
            Arrays.sort(scores, (s1, s2) -> Double.compare(s2[0], s1[0]));
            for (int i=0; i<N; i++) {
                int idx = (int)scores[i][1];
                grades[idx] = rating[i / (N/10)];
            }
            System.out.println("#" + test_case + " " + grades[K-1]);
		}
	}
}