/*
 * [문제] SWEA 1230 [SW 문제해결 기본] 8일차 - 암호문3
 * [유형] 리스트 / 시뮬레이션 (삽입, 삭제, 추가)
 * [핵심 아이디어]
 *   1. 암호문을 ArrayList 로 관리한다.
 *   2. I 는 loc 위치에 삽입, D 는 loc 위치에서 cnt 개 삭제, A 는 맨 뒤에 cnt 개 추가한다.
 *   3. 모든 명령 후 앞 10 개를 출력한다.
 * [시간복잡도] O(M * N)
 * [코테 가이드]
 *   - 명령별로 입력 형식이 다르다(A 는 loc 이 없다). 명령 문자를 읽은 뒤 필요한 값만 읽는다.
 *   - 암호문2 와 같은 틀에 A 명령 분기만 추가하면 된다.
 */

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;

class Solution
{
    private static void insert(List<Integer> li, int loc, int cnt, List<Integer> source) {
        li.addAll(loc, source);
    }

    private static void delete(List<Integer> li, int loc, int cnt) {
        while (loc < li.size() && cnt-- > 0)
            li.remove(loc);
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = sc.nextInt();
            List<Integer> cipherNums = new ArrayList<>();
            for (int i=0; i<N; i++) cipherNums.add(sc.nextInt());
            int M = sc.nextInt();
            for (int i=0; i<M; i++) {
                String command = sc.next();
                if (command.equals("I")) {
                    int loc = sc.nextInt();
                    int cnt = sc.nextInt();
                    List<Integer> soucre = new ArrayList<>();
                    for (int j=0; j<cnt; j++) {
                        soucre.add(sc.nextInt());
                    }
                    insert(cipherNums, loc, cnt, soucre);
                } else if (command.equals("A")) {
                    int cnt = sc.nextInt();
                    for (int j=0; j<cnt; j++) cipherNums.add(sc.nextInt());
                } else {
                    int loc = sc.nextInt();
                    int cnt = sc.nextInt();
                    delete(cipherNums, loc, cnt);
                }
            }
            System.out.print("#" + test_case + " ");
            for (int i=0; i<10; i++) System.out.print(cipherNums.get(i) + " ");
            System.out.println();

		}
	}
}