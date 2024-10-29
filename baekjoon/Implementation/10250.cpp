#include <iostream>
using namespace std;

int main(void)
{
	int t, h, w, n;
	cin >> t;
	while (t--)
	{
		cin >> h >> w >> n;
		int a = n % h;
		int b = n / h + 1;
		if (a == 0) {
			a = h;
			b -= 1;
		}
		cout << a;
		if (b <= 9) cout << "0";
		cout << b << '\n';
	}
}