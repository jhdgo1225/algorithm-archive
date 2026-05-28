import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> count = new HashMap<>();
        for (String[] info: clothes) {
            String sort = info[1];
            if (!count.containsKey(sort)) count.put(sort, 1);
            else count.put(sort, count.get(sort) + 1);
        }
        String[] keys = count.keySet().toArray(new String[0]);
        for (String key: keys) answer *= (count.get(key) + 1);
        answer -= 1;
        return answer;
    }
}