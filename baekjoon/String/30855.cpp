#include <iostream>
#include <vector>
using namespace std;
using ll = long long;

int N, idx;
char arr[101];

void input()
{
	cin >> N;
	for (int i=0; i<N; i++) cin >> arr[i];
}

void ft_exit()
{
	cout << "-1\n";
	exit(0);
}

ll gcd(ll p, ll q)
{
	ll tmp;
	while (q != 0)
	{
		tmp = p % q;
		p = q;
		q = tmp;
	}
	return (p);
}

pair<ll, ll> fraction(vector<pair<ll, ll> >& vc) {
	ll p = vc[1].first * vc[2].second;
	ll q = vc[1].second * vc[2].first;
	ll k = gcd(p,q);
	p /= k;
	q /= k;
	ll lcm = vc[0].second * q / gcd(vc[0].second, q);
	pair<ll, ll> res;
	res.first = (vc[0].first * (lcm / vc[0].second)) + (p * (lcm / q));
	res.second = lcm;
	k = gcd(res.first, res.second);
	res.first /= k;
	res.second /= k;
	return (res);
}

pair<ll, ll> func()
{
	bool flag = false;
	vector<pair<ll, ll>> vc;
	if (arr[idx] == '(') {
		flag = true;
		idx++;
	}
	while (idx < N && arr[idx] != ')')
	{
		if (arr[idx] == '(') vc.push_back(func());
		else vc.push_back({arr[idx] - 48, 1});
		if (idx == N) break;
		idx++;
	}
	if (!flag || (idx == N) || vc.size() != 3) ft_exit();
	if (N - 1 == idx && arr[idx] == ')') idx++;
	return (fraction(vc));
}

int main()
{
	input();
	pair<ll, ll> res = func();
	if (idx != N && arr[idx] == ')') ft_exit();
	cout << res.first << ' ' << res.second;
}