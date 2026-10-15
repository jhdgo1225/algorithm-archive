import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int firstCnt = 0;
        boolean[] firstNumberChecker = new boolean[arr.length];
        for (int i=0; i<arr.length; i++) {
            if (i == 0 || arr[i-1] != arr[i]) {
                firstNumberChecker[i] = true;
                firstCnt++;
            }
        }
        int[] result = new int[firstCnt];
        int pointer = 0;
        for (int i=0; pointer < firstCnt && i < arr.length; i++) {
            if (firstNumberChecker[i]) {
                result[pointer++] = arr[i];
            }
        }
        return result;
    }
}