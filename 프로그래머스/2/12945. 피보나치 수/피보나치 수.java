class Solution {
    // "피보나치 수열" 풀이방법
    // ---------------------------------
    // "피보나치 수열" 설명
    // f(n) = f(n-1) + f(n-2) (n >= 2, f(0) = 0, f(1) = 1)
    // ---------------------------------
    // 1. 반복문
    // f(n-2) -> n_2
    // f(n-1) -> n_1
    // f(n) -> answer
    // n 씩 증가할 때마다 f(n-1) + f(n-2) 더한 값 answer에 저장
    // f(n-2)은 f(n-1) 값으로 대체, f(n-1) 값은 answer 값으로 대체
    // => f(n-2) = f(n-1) -> n_2 = n_1
    // => f(n-1) = f(n)   -> n_1 = answer
    public int solution(int n) {
        int n_2=0, n_1=1, answer=0;
        for (int i=2; i<=n; i++) {
            answer = (n_1 + n_2) % 1234567;
            n_2 = n_1;
            n_1 = answer;
        }
        return answer;
    }
}