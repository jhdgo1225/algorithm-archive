#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/20551_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		int A,B,C; cin >> A >> B >> C;
		cout << "#" << i + 1 << ' ';
		if (B >= 2 && C >= 3) {
			if (A < B && B < C) cout << 0;
			else if (B < C) {
				cout << A - (B - 1) + C - (B + 1);
			} else {
				cout << A - (C - 2) + B - (C - 1);
			}
		} else {
			cout << -1;
		}
		cout << '\n';
	}
}
