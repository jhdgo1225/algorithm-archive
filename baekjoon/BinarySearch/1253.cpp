// 참고 자료: https://github.com/encrypted-def/basic-algo-lecture/blob/master/0x13/solutions/1253.cpp
#include <iostream>
#include <algorithm>
using namespace std;

int cnt, n, a[2001];

void solve(int i)
{
	for (int j=0; j<n; j++)
	{
		if (i == j) continue;
		int x = a[i] - a[j];
		int idx = lower_bound(a, a+n, x) - a;
		while (idx < n && a[idx] == x) {
			if (idx != i && idx != j) {
				cnt++;
				return ;
			}
			idx++;
		}
	}
}

int main()
{
	cin>>n;
	for (int i=0; i<n; i++) cin>>a[i];
	sort(a,a+n);
	for (int i=0; i<n; i++) solve(i);
	cout << cnt;
}