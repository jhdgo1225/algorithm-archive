#include <iostream>
#include <list>
#include <cmath>
using namespace std;

list<pair<int, int>> primeFactorization(int N)
{
	list<pair<int, int>> res;
	int root = int(sqrt(N));
	for (int i=2; i<=root + 1; i++)
	{
		while (N % i == 0) {
			if (res.empty() || res.back().first != i) res.push_back({i, 1});
            else res.back().second += 1;
			N /= i;
		}
	}
    if (N != 1)
		res.push_back({N, 1});
	return res;
}

int main()
{
	int N; cin >> N;
	list<pair<int, int>> res = primeFactorization(N);
	for (auto it: res)
		for (int i=0; i<it.second; i++)
			cout << it.first << '\n';
}