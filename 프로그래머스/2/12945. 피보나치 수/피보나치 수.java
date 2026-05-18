class Solution {
    private int[] dp;    // 메모이제이션 배열. 메모리(Memory) 소비. 반대로 실행 속도 최적화. Trade-Off 발생
    public int func(int n) {
        if (n <= 1) return n;
        if (dp[n] > 0) return dp[n];
        return dp[n] = (func(n-2) + func(n-1)) % 1234567;
    }
    public int solution(int n) {
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
        
        // int n_2=0, n_1=1, answer=0;
        // for (int i=2; i<=n; i++) {
        //     answer = (n_1 + n_2) % 1234567;
        //     n_2 = n_1;
        //     n_1 = answer;
        // }
        
        // 2. 재귀
        // 재귀 함수를 정의한 다음, 호출 함수 본문의 func(n-2), func(n-1) 순서대로 호출
        // 각각 호출하면 또 다시 그 호출 함수 본문의 func(n-2), func(n-1) 순서대로 호출
        // 탈출 조건(Base Condition): n이 1, 혹은 0인 경우 그대로 값을 return
        // 그러나 탈출 조건을 이것만 결정하면 안 된다. 호출 함수가 반복되는 경우가 발생하는데, 이때 시간복잡도 O(2^n)이 발생한다.
        // 따라서 각 경우에 대한 결과를 메모리에 담아서 중복 호출을 방지하는 방법을 이용한다. 이 기법을 "메모이제이션(Memoization)"이라고 한다.
        dp = new int[n + 1];    // 메모이제이션 배열 동적 할당
        int answer = func(n);   // 재귀 함수 호출
        return answer;
    }
}