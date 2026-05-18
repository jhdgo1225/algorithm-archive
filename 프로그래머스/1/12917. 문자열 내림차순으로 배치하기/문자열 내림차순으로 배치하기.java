import java.util.*;

class Solution {
    public String solution(String s) {
        // 1. String 타입 char 배열로 변환
        char[] chars = s.toCharArray();
        // 2. 오름차순 정렬 (대문자가 먼저, 그 다음에 소문자)
        Arrays.sort(chars);
        // 3. 오름차순 정렬된 문자 배열
        //  -> 문자열로 변환
        //  -> StringBuilder로 변환 (StringBuilder: 새로운 객체 생성없이 기존 문자열 객체 변경 가능)
        //  -> 역순으로 변경
        //  -> String으로 다시 변경
        String answer = new StringBuilder(new String(chars)).reverse().toString();
        return answer;
    }
}