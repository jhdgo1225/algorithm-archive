#include <iostream>
using namespace std;

void swap(int &a, int &b)
{
	a = a ^ b;
	b = a ^ b;
	a = a ^ b;
}

int gcd(int a, int b)
{
	int p = a, q = b;
	while (q != 0)
	{
		int c = p % q;
		p = q;
		q = c;
	}
	return p;
}

int main()
{
	int a, b; cin >> a >> b;
	if (a < b) swap(a, b);
	int gcd_res = gcd(a, b);
	cout << gcd_res << '\n';
	cout << (a * b) / gcd_res;
}
