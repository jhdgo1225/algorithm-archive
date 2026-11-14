import java.util.*;

class Solution {
    private void dfs(int cur, int max, String curStr, String[] charList, List<String> words) {
        if (cur == max) {
            words.add(curStr);
            return ;
        }
        for (int i=0; i<charList.length; i++) {
            dfs(cur + 1, max, curStr + charList[i], charList, words);
        }
    }
    public int solution(String word) {
        List<String> words = new ArrayList<>();
        String[] charList = {"A", "E", "I", "O", "U"};
        for (int i=1; i<=charList.length; i++)
            dfs(0, i, "", charList, words);
        Collections.sort(words);
        for (int i=0; i<words.size(); i++)
            if (words.get(i).equals(word))
                return i + 1;
        return 0;
    }
}