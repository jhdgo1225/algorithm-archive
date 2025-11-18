#include <iostream>
using namespace std;

int main()
{
	freopen("./22979_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		string str; cin >> str;
		long long a, tmp,move=0; cin >> a;
		for (int i=0; i<a; i++){
			cin >> tmp; move += tmp;
		}
		move = move % (long long)str.length();
		for (int i=0; i<str.length(); i++) {
			if (move > 0) cout << str[(i + move) % str.length()];
			else if (move < 0) cout << str[(i + str.length() + move) % str.length()];
			else cout << str[i];
		}
		cout << '\n';
	}
}
