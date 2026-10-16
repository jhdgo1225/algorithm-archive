import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String player : participant) map.put(player, map.getOrDefault(player, 0) + 1);
        for (String player : completion) {
            map.put(player, map.get(player) - 1);
            if (map.get(player) == 0) map.remove(player);
        }
        return map.keySet().toArray(new String[0])[0];
    }
}