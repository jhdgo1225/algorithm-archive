import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int idx = 0, h = 0, hIndex = 0;
        while (idx < n) {
            if (citations[idx] >= h && n - idx >= h) {
                hIndex = h++;
            } else {
                idx++;
            }
        }
        return hIndex;
    }
}