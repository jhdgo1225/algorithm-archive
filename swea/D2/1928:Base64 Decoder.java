/*
 * [문제] SWEA 1928 Base64 Decoder
 * [유형] 구현 / 비트 연산
 * [핵심 아이디어]
 *   1. Base64 문자 4개 = 6bit * 4 = 24bit = 원문 3바이트.
 *   2. 각 문자를 0~63 값으로 변환해 tmp1 을 6bit씩 왼쪽으로 밀며 합친다.
 *   3. 합쳐진 24bit 에서 8bit(mask 0xFF)씩 3번 뽑아 문자로 만들고 앞쪽에 붙여 순서를 복원한다.
 * [시간복잡도] O(L)
 * [코테 가이드]
 *   - Base64 값 매핑: A-Z=0~25, a-z=26~51, 0-9=52~61, +=62, /=63.
 *   - 실전에서는 java.util.Base64.getDecoder().decode() 를 쓰는 것이 가장 빠르다(허용 여부 확인).
 *   - 문자열 += 반복은 느릴 수 있으므로 길이가 크면 StringBuilder 를 사용한다.
 */

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		sc.nextLine();
        for (int test_case=1; test_case<=T; test_case++) {
            String str = sc.nextLine();
            String result = "", help = "";
            int tmp1 = 0, tmp2=0;
            for (int i=0; i<str.length(); i+=4) {
                for (int j=i; j<i+4; j++) {
                    char ch = str.charAt(j);
                    if ('A' <= ch && ch <= 'Z') tmp2 = ch - 'A';
                    else if ('a' <= ch && ch <= 'z') tmp2 = ch - 'a' + 26;
                    else if ('0' <= ch && ch <= '9') tmp2 = ch - '0' + 52;
                    else if (ch == '+') tmp2 = 62;
                    else if (ch == '/') tmp2 = 63;
                    tmp1 <<= 6;
                    tmp1 |= tmp2;
                }
                for (int j=0; j<3; j++) {
                    int mask = 0b11111111;
                    char ch = (char)(tmp1 & mask);
                    help = ch + help;
                    tmp1 >>= 8;
                }
                result += help;
                help = "";
            }
            System.out.println("#" + test_case + " " + result);
        }
	}
}