import java.util.*;

class Solution {
    public String solution(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String answer = new StringBuilder(new String(chars)).reverse().toString();
        return answer;
    }
}