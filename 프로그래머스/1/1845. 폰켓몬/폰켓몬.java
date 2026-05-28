import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        // 폰켓몬의 종류를 중복 없이 저장하기 위한 HashSet
        // HashSet은 같은 값을 여러 번 추가해도 하나만 저장한다.
        HashSet<Integer> phoneketmon = new HashSet<>();

        // nums 배열을 순회하면서 폰켓몬 번호를 HashSet에 추가한다.
        // 같은 번호의 폰켓몬은 중복 저장되지 않으므로,
        // 최종적으로 phoneketmon에는 서로 다른 종류의 폰켓몬만 남는다.
        for (int num : nums) {
            phoneketmon.add(num);
        }

        // 선택할 수 있는 폰켓몬 종류의 최대 개수는
        // 서로 다른 폰켓몬 종류의 개수이다.
        answer = phoneketmon.size();

        // 하지만 문제 조건상 전체 폰켓몬 중 절반만 선택할 수 있다.
        // 따라서 서로 다른 종류가 아무리 많아도 nums.length / 2개까지만 선택 가능하다.
        if (phoneketmon.size() > nums.length / 2) {
            answer = nums.length / 2;
        }

        return answer;
    }
}