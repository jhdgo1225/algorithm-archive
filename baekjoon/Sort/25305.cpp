#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool compare(int a, int b)
{
	return a > b;
}

int main()
{
	int num,sum=0,N,k; cin >> N >> k;
	vector<int> sorting;
	while (N--) {
		cin >> num; sorting.push_back(num);
	}
	sort(sorting.begin(), sorting.end(), compare);
	cout << sorting[k-1];
}
