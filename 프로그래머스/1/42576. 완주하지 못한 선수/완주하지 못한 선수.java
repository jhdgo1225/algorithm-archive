import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String player : participant) {
            if (!map.containsKey(player)) map.put(player, 0);
            map.computeIfPresent(player, (_, v) -> v + 1);
        }
        for (String player : completion) {
            map.computeIfPresent(player, (_, v) -> v - 1);
            if (map.get(player) == 0) map.remove(player);
        }
        return map.keySet().toArray(new String[0])[0];
    }
}