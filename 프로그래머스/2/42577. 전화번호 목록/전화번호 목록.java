import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    /*
     * Set 자료구조 설명
     *
     * Set은 중복을 허용하지 않는 자료구조이다.
     *
     * List나 배열은 같은 값을 여러 번 저장할 수 있지만,
     * Set은 같은 값을 여러 번 추가해도 하나만 저장된다.
     *
     * 예를 들어 아래 값을 Set에 추가하면,
     *
     * "119"
     * "119"
     * "97674223"
     *
     * 실제 Set에는 다음과 같이 저장된다.
     *
     * "119"
     * "97674223"
     *
     * 즉, Set은 "값의 존재 여부"를 빠르게 확인할 때 자주 사용한다.
     *
     *
     * HashSet 설명
     *
     * HashSet은 Hash 방식을 사용하는 Set이다.
     *
     * HashSet은 값을 저장할 때 해시값을 이용하므로,
     * 특정 값이 존재하는지 contains()로 빠르게 확인할 수 있다.
     *
     * 주요 메서드:
     * - add(value): 값을 Set에 추가한다.
     * - contains(value): 해당 값이 Set에 존재하는지 확인한다.
     * - remove(value): 해당 값을 Set에서 제거한다.
     * - isEmpty(): Set이 비어 있는지 확인한다.
     * - size(): Set에 저장된 값의 개수를 확인한다.
     *
     *
     * 이 문제에서 HashSet을 사용하는 이유
     *
     * 이 문제는 어떤 전화번호가 다른 전화번호의 접두어인지 확인해야 한다.
     *
     * 예를 들어 "1195524421"이라는 전화번호가 있다면,
     * 이 번호의 접두어는 다음과 같다.
     *
     * "1"
     * "11"
     * "119"
     * "1195"
     * ...
     * "1195524421"
     *
     * 이 풀이에서는 긴 전화번호부터 확인하면서,
     * 각 전화번호의 모든 접두어를 HashSet에 저장한다.
     *
     * 이후 더 짧은 전화번호가 등장했을 때,
     * 그 전화번호가 이미 HashSet에 존재한다면
     * 이전에 등장한 긴 전화번호의 접두어였다는 의미가 된다.
     *
     * 따라서 HashSet은
     * "이 전화번호가 이전 번호의 접두어로 저장되어 있는가?"
     * 를 빠르게 확인하기 위해 사용된다.
     */

    public boolean solution(String[] phonebook) {
        // 기본값은 접두어 관계가 없다고 가정한다.
        boolean answer = true;

        // 전화번호를 내림차순으로 정렬한다.
        // 긴 번호가 먼저 나오도록 하여,
        // 이후 짧은 번호가 등장했을 때 이미 저장된 접두어인지 확인할 수 있다.
        Arrays.sort(phonebook, Collections.reverseOrder());

        // 지금까지 등장한 전화번호들의 모든 접두어를 저장할 HashSet
        // 중복된 접두어는 한 번만 저장된다.
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