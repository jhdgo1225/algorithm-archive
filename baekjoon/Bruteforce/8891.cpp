#include <iostream>
using namespace std;

int main()
{
	int T, a, b; cin >> T;
	while (T--)
	{
		cin >> a >> b;
		int y1 = 1, y2 = 1;
		while (1 + y1*(y1+1)/2 <= a) y1++;
		while (1 + y2*(y2+1)/2 <= b) y2++;
		int x1 = 1 + (a - (1 + y1*(y1-1)/2));
		int x2 = 1 + (b - (1 + y2*(y2-1)/2));
		y1 -= x1 + 1;
		y2 -= x2 + 1;
		int x3 = x1 + x2;
		int y3 = y1 + y2;
		cout << 1 + (y3 + (x3 - 1)) * ((y3 + (x3 - 1)) - 1) / 2 + (x3 - 1) << '\n';
	}
}