#include <iostream>
using namespace std;
int w[101];
int v[101];
int dp[101][100001];

void func(int n, int k)
{
	if (n <= 0 || k <= 0 || dp[n][k])
		return ;
	func(n-1, k); func(n-1, k - w[n]);
	if (k >= w[n]) dp[n][k] = max(dp[n-1][k], dp[n-1][k-w[n]]+v[n]);
	else dp[n][k] = dp[n-1][k];
}

int main()
{
	int N, K;
	cin >> N >> K;
	for (int i=1; i<=N; i++)
		cin >> w[i] >> v[i];
	func(N, K);
	cout << dp[N][K];
}