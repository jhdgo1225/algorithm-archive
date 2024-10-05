// Baekjoon 10844번: 쉬운 계단 수
#include <iostream>
#include <vector>
using namespace std;

vector<int>	easyStairs(int depth)
{
	vector<int> res(10);
	if (depth <= 1)
	{
		for (int i=1; i<=9; i++) res[i] = 1;
		return (res);
	}
	vector<int> tmp = easyStairs(depth - 1);
	for (int i=0; i<10; i++)
	{
		if (i - 1 >= 0) res[i-1] = (res[i-1] + tmp[i]) % 1000000000;
		if (i + 1 <= 9) res[i+1] = (res[i+1] + tmp[i]) % 1000000000;
	}
	return (res);
}

int main(void)
{
	int n, sum=0; cin >> n;
	vector<int> res = easyStairs(n);
	for (int i=0; i<res.size(); i++)
		sum = (sum + res[i]) % 1000000000;
	cout << sum;
}