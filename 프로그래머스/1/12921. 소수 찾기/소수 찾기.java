

class Solution {
    public int solution(int n) {
        boolean[] seive = new boolean[n + 1];
        for (int i=0; i<=n; i++) seive[i] = true;
        seive[0] = seive[1] = false;
        for (int i=2; i<=n; i++) {
            if (!seive[i]) continue;
            for (int j=2; i*j<=n; j++) {
                seive[i*j] = false;
            }
        }
        int answer = 0;
        for (int i=1; i<=n; i++) {
            if (seive[i]) {
                answer += 1;
            }
        }
        return answer;
    }
}
