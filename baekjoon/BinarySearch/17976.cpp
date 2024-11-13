#include <iostream>
#include <algorithm>
using namespace std;
using ll = long long;

pair<ll, ll> threads[100001];
ll knots[100001];
int n;

bool func(int dis)
{
	knots[0] = threads[0].first;
	for (int i=1; i<n; i++)
	{
		if (knots[i-1] + dis > threads[i].second)
			return (false);
		else if (knots[i-1] + dis <= threads[i].first)
			knots[i] = threads[i].first;
		else if (knots[i-1] + dis > threads[i].first)
			knots[i] = knots[i-1] + dis;
	}
	return (true);
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	ll a,b;cin>>n;
	for (int i=0; i<n; i++)
	{
		cin >> a >> b;
		threads[i].first = a;
		threads[i].second = a + b;
	}
	sort(threads, threads + n);
	for (int i=0; i<n; i++)
		knots[i] = threads[i].first;
	ll left = 0, right = 2000000001, res = 0;
	while (left <= right)
	{
		ll mid = (left + right) / 2;
		bool tmp = func(mid);
		if (!tmp) right = mid - 1;
		else {
			res = max(res, mid);
			left = mid + 1;
		}
	}
	cout << res;
}