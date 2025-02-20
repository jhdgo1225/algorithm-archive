#include <iostream>
using namespace std;

string str;
int res = -1;
void func(int s, int e, int k)
{
	if (s >= e) {
		if (res == -1 || res > k) res = k;
		return ;
	}
	if (str[s] == str[e]) func(s + 1, e - 1, k);
	else {
		if (k == 3) return ;
		func(s, e - 1, k+1);
		func(s + 1, e, k+1);
	}
}

int main()
{
	cin >> str;
	int s = 0, e = str.size() - 1;
	func(s, e, 0);
	cout << res;
}