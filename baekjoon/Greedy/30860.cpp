#include <iostream>
using namespace std;

int N, x, y, cnt;
int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> N;
	int c = 0;
	for (int i=0;i<N;i++)
	{
		cin >> x >> y;
		if (c > y) {
			cnt++;
			c = 0;
		}
		c = max(c, x);
	}
	cout << cnt + 1;
}
