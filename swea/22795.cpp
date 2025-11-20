#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	freopen("./inputs/22795_input.txt", "r", stdin);
	int T; cin >> T;
	while (T--)
	{
		int a[6], sum = 0;
		for (int i=0; i<6; i++) {
			cin >> a[i];
			sum += a[i];
		}
		sort(a, a+6);
		int seeker = a[5] + 1;
		while ((seeker + sum) % 7 != 0) seeker++;
		cout << seeker << '\n';
	}
}