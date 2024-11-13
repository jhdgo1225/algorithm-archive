#include <iostream>
#include <algorithm>
using namespace std;

int liquid[100001];
int main(void)
{
	int N, one, two, res = 2e9; cin >> N;
	for (int i=0; i<N; i++) cin >> liquid[i];
	for (int i=0; i<N-1; i++)
	{
		int st = i+1, en = N-1;
		while (st < en)
		{
			int mid = (st + en) >> 1;
			int tmp = abs(liquid[i] + liquid[mid]);
			if (tmp > abs(liquid[i] + liquid[mid + 1])) st = mid + 1;
			else if (tmp > abs(liquid[i] + liquid[mid - 1])) en = mid - 1;
			else {
				st = mid;
				break;
			}
		}
		if (abs(res) >= abs(liquid[i] + liquid[st]))
		{
			one = liquid[i];
			two = liquid[st];
			res = abs(liquid[i] + liquid[st]);
		}
	}
	cout << one << ' ' << two << '\n';
	return (0);
}
