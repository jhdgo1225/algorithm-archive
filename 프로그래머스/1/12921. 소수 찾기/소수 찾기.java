class Solution {
  public int solution(int n) {
      int answer = 0;     // n까지의 자연수 중 소수인 자연수 개수
      for(int i = 2; i <= n; i++){
          int j = 2;
          int cnt = 0;    // i의 약수 개수 (1과 자기 자신 제외)
          
          // 약수의 성질: 모든 약수는 가운데 약수를 기준으로 곱셈 연산에 대해 대칭을 이룬다.
          // 예시: 36 -> 1, 2, 3, 4, 6, 9, 12, 18, 36
          // 가운데 약수: 6 -> sqrt(36)
          // 약수를 알고 싶으면 가운데 약수(제곱근)를 기준으로 2부터 순회
          // 이 점을 활용해 아래 로직처럼 1, 자기 자신 이외에 다른 약수가 발견되지 않으면(cnt == 0) 소수로 판별(answer += 1)한다.
          
          while(j <= (int)Math.sqrt(i)){
              if(i % j == 0){
                cnt += 1;
                break;
              } 
              j += 1;
          }
          if(cnt == 0) answer += 1;
      }
      return answer;
  }
}