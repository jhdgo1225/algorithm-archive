#include <iostream>
using namespace std;

int dp[1001][1001];
int main(void)
{
	string a, b;
	cin >> a >> b;
	for (int i=1; i<=a.size(); i++)
		for (int j=1; j<=b.size(); j++)
			dp[i][j] = max(max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1] + (a[i] == b[j]));
	cout << dp[a.size()][b.size()];
}
