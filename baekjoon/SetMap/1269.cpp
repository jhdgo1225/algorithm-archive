#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
using namespace std;

int main()
{
	cin.tie(0);
    ios::sync_with_stdio(0);
	int N, M, num, cnt=0; cin >> N >> M;
	set<int> A;
	while (N--) {
		cin >> num;
		A.insert(num);
	}
	while (M--) {
		cin >> num;
		if (A.find(num) != A.end()) cnt++;
		A.insert(num);
	}
	cout << A.size() - cnt;
}