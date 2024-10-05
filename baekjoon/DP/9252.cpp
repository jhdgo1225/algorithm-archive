// Baekjoon 9252번: LCS 2
#include <iostream>
#include <stack>
using namespace std;

int dp[1001][1001];
int main(void)
{
	stack<char> seq;
	string a, b;
	cin >> a >> b;
	for (int i=1; i<=a.size(); i++)
		for (int j=1; j<=b.size(); j++)
			dp[i][j] = max(max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1] + (a[i-1] == b[j-1]));
	int cx = a.size(), cy = b.size();
	while (cx > 0 && cy > 0)
	{
		if ((dp[cx][cy] != dp[cx-1][cy]) && (dp[cx][cy] != dp[cx][cy-1]))
		{
			seq.push(a[cx-1]);
			cx--; cy--;
		}
		else (dp[cx-1][cy] > dp[cx][cy-1]) ? cx-- : cy--;
	}
	cout << dp[a.size()][b.size()] << endl;
	while (dp[a.size()][b.size()] && !seq.empty()) {
		cout << seq.top(); seq.pop();
	}
}
