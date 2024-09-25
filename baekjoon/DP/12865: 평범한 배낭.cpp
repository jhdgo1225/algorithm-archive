#include <iostream>
using namespace std;

int weights[101];
int values[101];
int dp[101][100001];
int main()
{
	int n, k;
	cin >> n >> k;
	for (int i=1; i<=n; i++)
		cin >> weights[i] >> values[i];
	for (int i=1; i<=k; i++)
	{
		for (int j=1; j<=n; j++)
		{
			if (weights[j] <= i) dp[j][i] = max(dp[j-1][i], dp[j-1][i - weights[j]] + values[j]);
			else dp[j][i] = dp[j-1][i];
		}
	}
	cout << dp[n][k];
}
