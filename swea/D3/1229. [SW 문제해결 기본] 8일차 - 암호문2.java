/*
 * [문제] SWEA 1229 [SW 문제해결 기본] 8일차 - 암호문2
 * [유형] 리스트 / 시뮬레이션 (삽입, 삭제)
 * [핵심 아이디어]
 *   1. 암호문을 ArrayList 로 관리한다.
 *   2. I 명령은 addAll(loc, 새 숫자들), D 명령은 loc 위치에서 remove 를 cnt 번 한다.
 *   3. 모든 명령 후 앞 10 개를 출력한다.
 * [시간복잡도] O(M * N)
 * [코테 가이드]
 *   - 삭제는 같은 인덱스 loc 에서 cnt 번 remove 하면 된다(뒤 원소가 앞으로 당겨지므로).
 *   - 명령마다 읽어야 하는 입력 개수가 다르니 명령 문자를 먼저 읽고 분기한다.
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
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T = 10;
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = sc.nextInt();
            List<Integer> cipherNums = new ArrayList<>();
            for (int i=0; i<N; i++) cipherNums.add(sc.nextInt());
            int M = sc.nextInt();
            for (int i=0; i<M; i++) {
                String command = sc.next();
                int loc = sc.nextInt();
                int cnt = sc.nextInt();
                if (command.equals("I")) {
                    List<Integer> soucre = new ArrayList<>();
                    for (int j=0; j<cnt; j++) {
                        soucre.add(sc.nextInt());
                    }
                    insert(cipherNums, loc, cnt, soucre);
                } else {
                    delete(cipherNums, loc, cnt);
                }
            }
            System.out.print("#" + test_case + " ");
            for (int i=0; i<10; i++) System.out.print(cipherNums.get(i) + " ");
            System.out.println();

		}
	}
}