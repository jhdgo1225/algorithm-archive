#include <iostream>
#include <cmath>
using namespace std;

void primeFactorization(int N)
{
	int root = int(sqrt(N));
	for (int i=2; i<=root + 1; i++)
	{
		while (N % i == 0) {
			cout << i << '\n';
			N /= i;
		}
	}
    if (N != 1) cout << N << '\n';
}

int main()
{
	int N; cin >> N;
	primeFactorization(N);
}