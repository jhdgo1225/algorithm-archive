import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();
        for (int elem : arr) {
            if (list.isEmpty() || list.get(list.size() - 1) != elem) {
                list.add(elem);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}