#include <iostream>
using namespace std;

int main()
{
	int N=100;
	cout << N << '\n';
	for (int i=1; i<=N; i++)
	{
		for (int j=1; j<=N; j++)
		{
			if (i == j) cout << 0 << ' ';
			else if (i == N || j == N) cout << 1 << ' ';
			else cout << 1000 << ' ';
		}
		cout << '\n';
	}
}
