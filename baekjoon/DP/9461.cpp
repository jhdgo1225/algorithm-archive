#include <iostream>
using namespace std;

long long dp[101];
int main()
{
	int n, k;
	cin >> n;
	dp[1] = dp[2] = dp[3] = 1;
	dp[4] = 2;
	for (int i=5; i<=100; i++)
		dp[i] = dp[i-1] + dp[i-5];
	while (n--) {
		cin >> k; cout << dp[k] << '\n';
	}
}