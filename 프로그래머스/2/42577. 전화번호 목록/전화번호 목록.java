import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public boolean solution(String[] phonebook) {
        // 기본값은 접두어 관계가 없다고 가정한다.
        boolean answer = true;

        // 전화번호를 내림차순으로 정렬한다.
        // 긴 번호가 먼저 나오도록 하여,
        // 이후 짧은 번호가 등장했을 때 이미 저장된 접두어인지 확인할 수 있다.
        Arrays.sort(phonebook, Collections.reverseOrder());

        // 지금까지 등장한 전화번호들의 모든 접두어를 저장할 HashSet
        HashSet<String> set = new HashSet<>();

        // 정렬된 전화번호 목록을 하나씩 확인한다.
        for (String phone : phonebook) {

            // 현재 전화번호가 이미 set에 존재한다면,
            // 이전에 등장한 더 긴 전화번호의 접두어였다는 의미이다.
            // 따라서 어떤 번호가 다른 번호의 접두어인 경우이므로 false를 반환해야 한다.
            if (set.contains(phone)) {
                answer = false;
                break;
            }

            // 현재 전화번호의 모든 접두어를 set에 저장한다.
            // 예를 들어 phone이 "1195524421"이면
            // "1", "11", "119", ... , "1195524421"까지 저장된다.
            for (int i = 0; i < phone.length(); i++) {
                set.add(phone.substring(0, i + 1));
            }
        }

        return answer;
    }
}