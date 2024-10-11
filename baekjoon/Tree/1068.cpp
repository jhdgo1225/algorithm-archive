// 참고 코드: https://www.acmicpc.net/source/370012
// BOJ 1068번: 트리
#include <iostream>
using namespace std;

int n, leaf, d, p[51], c[51];

int del(int node)
{
	int res=0,l=1;
	for (int i=0; i<n; i++)
	{
		if (p[i] == node)
		{
			l=0;
			res += del(i);
		}
	}
	return (res + l);
}

int main()
{
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> p[i];
		if (p[i] >= 0)
			c[p[i]]++;
	}
	for (int i=0; i<n; i++)
		if (c[i] == 0) leaf++;
	cin >> d;
	if (c[p[d]] == 1) leaf++;
	cout << leaf - del(d);
}