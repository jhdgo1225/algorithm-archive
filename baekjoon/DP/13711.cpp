// Baekjoon 13711번: LCS 4
#include <iostream>
#include <algorithm>
using namespace std;

int idx[100001];
int res[100001];
int main()
{
	int n, a;
	cin >> n;
	for (int i=0; i<n; i++) {
		cin >> a;
		idx[a] = i;
	}
	for (int i=0; i<n; i++)
	{
		cin >> a;
		res[i] = n;
		*lower_bound(res, res+i, idx[a]) = idx[a];
	}
	cout << lower_bound(res, res+n, n) - res;
}
