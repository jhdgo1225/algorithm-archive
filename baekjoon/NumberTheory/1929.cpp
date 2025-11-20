#include <iostream>
#include <vector>
using namespace std;

void assignPrime(vector<bool>& isNotPrime, int maxNum)
{
	isNotPrime[0] = isNotPrime[1] = true;
	for (int i = 2; i <= maxNum; i++) {
		if (isNotPrime[i]) continue;
		int j = 2;
		while (i * j <= maxNum) {
			if (!isNotPrime[i * j]) isNotPrime[i * j] = true;
			j++;
		}
	}
}

int main()
{
	int N, M; cin >> N >> M;
	vector<bool> isNotPrime(M + 1, false);
	assignPrime(isNotPrime, M);
	for (int i=N; i<=M; i++)
		if (!isNotPrime[i])
			cout << i << '\n';
}