import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] trio = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] cycle = {0, 0, 0};
        int[][] ansCnt = {{1, 0}, {2, 0}, {3, 0}};
        for (int i=0; i<answers.length; i++) {
            for(int j=0;  j<3; j++) {
                if (answers[i] == trio[j][cycle[j]]) {
                    ansCnt[j][1]++;
                }
                cycle[j] = (cycle[j] + 1) % trio[j].length;
            }
        }
        Arrays.sort(ansCnt, (a, b) -> {
            if (a[0] == b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });
        List<Integer> tmp = new ArrayList<>(); tmp.add(ansCnt[0][0]);
        for (int i=1; i<3; i++) if (ansCnt[0][1] == ansCnt[i][1]) tmp.add(ansCnt[i][0]);
        int[] answer = tmp.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return answer;
    }
}