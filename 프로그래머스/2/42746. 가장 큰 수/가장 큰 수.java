import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int zeroCnt = 0;
        String[] numToStr = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            numToStr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numToStr, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String str : numToStr) {
            sb.append(str);
            if (str.compareTo("0") == 0) zeroCnt++;
        }
        if (zeroCnt == numbers.length) return "0";
        return sb.toString();
    }
}