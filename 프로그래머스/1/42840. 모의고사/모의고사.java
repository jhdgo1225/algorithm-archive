import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int tmpScore = 0;
        ArrayList<Integer> goodStus = new ArrayList<>();
        int[] supos = new int[3];
        int[][] suposMethod = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] countPerRound = {5, 8, 10};
        
        for (int i=0; i<3; i++) supos[i] = 0;
        for (int i=0; i<answers.length; i++) {
            for (int j=0; j<3; j++) {
                if (suposMethod[j][i % countPerRound[j]] == answers[i])
                    supos[j] += 1;
            }
        }
        for (int i=0; i<3; i++) {
            if (tmpScore < supos[i] || tmpScore == supos[i]) {
                if (tmpScore < supos[i]) {
                    tmpScore = supos[i];
                    goodStus.clear();
                }
                goodStus.add(i + 1);
            }
        }
        return goodStus.stream()
                   .mapToInt(Integer::intValue)
                   .toArray();
    }
}