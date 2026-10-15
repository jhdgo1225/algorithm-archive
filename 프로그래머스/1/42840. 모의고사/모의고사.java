import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answer) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] countPerRound = {5, 8, 10};
        int scoreA=0, scoreB=0, scoreC=0;
        for(int i=0; i<answer.length; i++) {
            if(answer[i] == a[i%countPerRound[0]]) scoreA++;
            if(answer[i] == b[i%countPerRound[1]]) scoreB++;
            if(answer[i] == c[i%countPerRound[2]]) scoreC++;
        }
        int maxScore = Math.max(scoreA, Math.max(scoreB, scoreC));
        ArrayList<Integer> list = new ArrayList<>();
        if(maxScore == scoreA) {list.add(1);}
        if(maxScore == scoreB) {list.add(2);}
        if(maxScore == scoreC) {list.add(3);}
        return list.stream().mapToInt(i->i.intValue()).toArray();
    }
}