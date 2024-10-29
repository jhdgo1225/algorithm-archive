#include <iostream>
#include <deque>
using namespace std;

int a[5000001];
int main()
{
	ios::sync_with_stdio(0);
    cin.tie(0);
	deque<pair<int, int> > q;
	deque<int> nums;
	int n, L;
	cin >> n >> L;
	for (int i=0; i<n; i++)
		cin >> a[i];
	for (int i=0; i<n; i++)
	{
		if (i >= L && q.front().second == i - L) q.pop_front();
		while (!q.empty() && q.back().first > a[i])
			q.pop_back();
		q.push_back({a[i], i});
		cout << q.front().first << ' ';
	}
}
