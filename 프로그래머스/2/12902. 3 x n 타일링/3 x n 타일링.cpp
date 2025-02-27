#include <string>
#include <vector>
using namespace std;

long long dp[5001];
long long solution(int n) {
    dp[0] = 1;
    long long add = 0;
    const int MOD = 1000000007;
    for (int i = 2; i <= n; i+=2) {
        dp[i] = ((dp[i - 2] * 3) + (add * 2)) % MOD;
        add += dp[i - 2];
    }
    return dp[n];
}