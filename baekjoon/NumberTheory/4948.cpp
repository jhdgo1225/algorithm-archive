#include <iostream>
using namespace std;

void assignPrime(bool (&isNotPrime)[250001])
{
	isNotPrime[0] = isNotPrime[1] = true;
	for (int i=2; i<=250000; i++) {
		if (isNotPrime[i]) continue;
		int j=2;
		while (i * j <= 250000) {
			if (!isNotPrime[i * j]) isNotPrime[i * j] = true;
			j++;
		}
	}
}

int main()
{
	int n;
	bool isNotPrime[250001] = {false, };
	assignPrime(isNotPrime);
	while (cin >> n)
	{
		if (!n) break;
		int cnt = 0;
		for (int i=n+1; i<=2*n; i++) cnt += (!isNotPrime[i]);
		cout << cnt << '\n';
	}
}