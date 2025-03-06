#include <iostream>
using namespace std;

int L[21], J[21];
int dp[21][100];
int main()
{
	int n; cin >> n;
	for (int i=1; i<=n; i++) cin >> L[i];
	for (int i=1; i<=n; i++) cin >> J[i];
	for (int i=1; i<=n; i++)
		for (int j=1; j<100; j++)
			dp[i][j] = (j >= L[i]) ? max(dp[i-1][j], dp[i-1][j-L[i]] + J[i]) : dp[i-1][j];
	cout << dp[n][99];
}
