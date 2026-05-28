import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        // 참가자 이름과 등장 횟수를 저장할 HashMap
        // key: 참가자 이름
        // value: 해당 이름을 가진 참가자의 수
        HashMap<String, Integer> map = new HashMap<>();

        // 참가자 배열을 순회하면서 이름별 참가자 수를 카운트한다.
        // 동명이인이 있을 수 있으므로 이름이 이미 존재하면 개수를 1 증가시킨다.
        for (String person : participant) {
            if (map.containsKey(person)) {
                map.put(person, map.get(person) + 1);
            } else {
                map.put(person, 1);
            }
        }

        // 완주자 배열을 순회하면서 해당 이름의 참가자 수를 1 감소시킨다.
        // 완주한 사람은 참가자 목록에서 제거된 것과 같은 의미이다.
        for (String person : completion) {
            if (map.containsKey(person)) {
                map.put(person, map.get(person) - 1);
            }
        }

        // 참가자 배열을 다시 순회하면서 값이 0이 아닌 사람을 찾는다.
        // 값이 0이 아니라는 것은 참가자는 있지만 완주하지 못했다는 의미이다.
        for (String person : participant) {
            if (map.get(person) != 0) {
                answer = person;
            }
        }

        return answer;
    }
}