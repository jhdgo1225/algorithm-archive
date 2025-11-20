#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	int num,sum=0,N=5;
	vector<int> sorting;
	while (N--) {
		cin >> num; sorting.push_back(num);
	}
	sort(sorting.begin(), sorting.end());
	for (auto it: sorting) {
		sum += it;
	}
	cout << sum / 5 << '\n' << sorting[2];
}
