#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/22039_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		int N; cin >> N;
		if (N % 3 == 1) cout << "impossible";
		else {
			if (N % 3 == 2) cout << "BA";
			for (int i=0; i<N/3; i++) cout << "BBA";
		}
		cout << '\n';
	}
}
