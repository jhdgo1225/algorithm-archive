#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	int N, num; cin >> N;
	vector<int> sorting;
	while (N--) {
		cin >> num; sorting.push_back(num);
	}
	sort(sorting.begin(), sorting.end());
	for (auto it: sorting) {
		cout << it << '\n';
	}
}
