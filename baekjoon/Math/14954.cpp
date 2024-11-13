#include <iostream>
using namespace std;

int f(int n)
{
	if (!n) return (0);
	return (n % 10) * (n % 10) + f(n / 10);
}

int main()
{
	int n,chk; cin >> n;
	while (1)
	{
		chk = f(n);
		if (chk == 1 || chk == 4)
			break;
		n = chk;
	}
	if (chk == 1) cout<<"HAPPY\n";
	else cout <<"UNHAPPY\n";
}