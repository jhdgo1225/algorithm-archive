class Solution {
    private int dfs(String begin, String target, String[] words, boolean[] visited, int step) {
        // System.out.println("(현재 단계: " + step + "단계) 시작 문자열: " + begin + ", 최종 문자열: " + target);
        if (begin.equals(target)) return step;
        int minStep = 0;
        for (int i=0; i<words.length; i++) {
            if (visited[i]) continue;
            int diffLetterCnt = 0;
            for (int j=0; j<begin.length(); j++) {
                if (begin.charAt(j) != words[i].charAt(j)) diffLetterCnt++;
            }
            if (diffLetterCnt == 1) {
                // System.out.println("(다음 단계: " + (step + 1) + "단계) !현재 문자열: " + begin + ", 다음 문자열: " + words[i]);
                visited[i] = true;
                int tmp = dfs(words[i], target, words, visited, step + 1);
                minStep = (minStep == 0) ? tmp : Math.min(minStep, tmp);
                visited[i] = false;
            }
        }
        return minStep;
    }
    
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        return dfs(begin, target, words, visited, 0);
    }
}