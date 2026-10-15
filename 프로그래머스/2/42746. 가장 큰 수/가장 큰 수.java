import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        int zeroCnt = 0;
        String[] numToStr = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            numToStr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numToStr, (a, b) -> (b + a).compareTo(a + b));
        for (String str : numToStr) {
            answer += str;
            if (str.compareTo("0") == 0) zeroCnt++;
        }
        if (zeroCnt == numbers.length) return "0";
        return answer;
    }
}