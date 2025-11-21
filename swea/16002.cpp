#include <iostream>
using namespace std;

int main()
{
	int T, a=999999999, b=1000000000;cin>>T;
	for (int i=0; i<T; i++) {
		int N; cin >> N;
		cout << "#" << i + 1 << ' ';
		if (N % 2) cout << a << ' ' << a - N;
		else cout << b << ' ' << b - N;
		cout << '\n';
	}
}