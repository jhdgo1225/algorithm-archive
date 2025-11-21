#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/20934_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		string S; cin >> S;
		int K, pos; cin >> K;
		for (int i=0; i<3; i++) if (S[i] == 'o') pos = i;
		if (pos != 1 && K > 0) {
			pos = 1;
			K--;
		}
		cout << "#" << i + 1 << ' ';
		if (K % 2) cout << 0;
		else cout << pos;
		cout << '\n';
	}
}