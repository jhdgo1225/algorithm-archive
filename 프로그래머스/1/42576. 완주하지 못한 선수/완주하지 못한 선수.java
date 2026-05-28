import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (String person : participant) {
            if (map.containsKey(person)) map.put(person, map.get(person) + 1);
            else map.put(person, 1);
        }
        for (String person : completion) if (map.containsKey(person)) map.put(person, map.get(person) - 1);
        for (String person : participant) if (map.get(person) != 0) answer = person;
        return answer;
    }
}