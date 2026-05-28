import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 조합의 총 개수를 계산하기 위한 변수
        // 곱셈을 해야 하므로 초기값을 1로 설정한다.
        int answer = 1;

        // 옷 종류별 개수를 저장할 HashMap
        // key: 옷 종류
        // value: 해당 종류에 속한 옷의 개수
        HashMap<String, Integer> count = new HashMap<>();

        // clothes 배열을 순회하면서 옷 종류별 개수를 센다.
        // info[0]: 옷 이름
        // info[1]: 옷 종류
        for (String[] info : clothes) {
            String sort = info[1];

            // 해당 종류가 처음 등장했다면 개수를 1로 저장한다.
            if (!count.containsKey(sort)) {
                count.put(sort, 1);
            }
            // 이미 등장한 종류라면 기존 개수에 1을 더한다.
            else {
                count.put(sort, count.get(sort) + 1);
            }
        }

        // HashMap에 저장된 옷 종류 목록을 배열로 변환한다.
        String[] keys = count.keySet().toArray(new String[0]);

        // 각 옷 종류별로 선택 가능한 경우의 수를 곱한다.
        // count.get(key): 해당 종류의 옷을 하나 선택하는 경우
        // + 1: 해당 종류의 옷을 선택하지 않는 경우
        for (String key : keys) {
            answer *= (count.get(key) + 1);
        }

        // 모든 옷을 선택하지 않는 경우는 문제 조건상 제외해야 한다.
        answer -= 1;

        return answer;
    }
}