#include <iostream>
using namespace std;

void assignPrime(bool (&isNotPrime)[1000001])
{
	isNotPrime[0] = isNotPrime[1] = true;
	for (int i=2; i<=1000000; i++) {
		if (isNotPrime[i]) continue;
		int j=2;
		while (i * j <= 1000000) {
			if (!isNotPrime[i * j]) isNotPrime[i * j] = true;
			j++;
		}
	}
}

int main()
{
	bool isNotPrime[1000001] = {false, };
	assignPrime(isNotPrime);
	int N,M; cin >> N >> M;
	for (int i=N; i<=M; i++)
		if (!isNotPrime[i])
			cout << i << '\n';
}