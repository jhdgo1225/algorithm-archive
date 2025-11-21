#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/18662_input.txt", "r", stdin);
	int T;cin>>T;
	for (int i=0; i<T; i++)
	{
		int a, b, c; cin >> a >> b >> c;
		cout << "#" << i + 1 << ' ';
		cout << fixed;
		cout.precision(1);
		cout << abs(b - (double)(a + c) / 2) << '\n';
	}
}