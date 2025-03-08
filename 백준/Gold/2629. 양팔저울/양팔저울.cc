#include <iostream>
using namespace std;

int choo[31];
int dp[40001];
int swaps[40001];
char res[10];
int main()
{
	int n, sum = 0; cin >> n;
	for (int i=1; i<=n; i++) {
		cin >> choo[i];
		sum += choo[i];
		for (int j=1; j<=sum; j++) {
			if (j == choo[i]) swaps[j] = 1;
			else if (dp[abs(choo[i] - j)]) swaps[j] = dp[abs(choo[i] - j)];
			else if (dp[choo[i] + j]) swaps[j] = dp[choo[i] + j];
			else swaps[j] = dp[j];
		}
		for (int j=1; j<=sum; j++) dp[j] = swaps[j];
	}
	int test, chk; cin >> test;
	for (int i=0; i<test; i++)
	{
		cin >> chk;
		res[i] = (dp[chk] ? 'Y' : 'N');
	}
	for (int i=0; i<test; i++) cout << res[i] << ' ';
	return 0;
}