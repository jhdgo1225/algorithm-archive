#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

int main()
{
	int num, N; cin >> N;
	vector<int> sorting, inputs;
	unordered_map<int, int> mapper;
	while (N--) {
		cin >> num; inputs.push_back(num);
	}
	sorting = inputs;
	sort(sorting.begin(), sorting.end());
	int idx = 0;
	for (auto it: sorting) {
		if (mapper.find(it) == mapper.end()) mapper[it] = idx++;
	}
	for (auto it: inputs) {
		cout << mapper[it] << ' ';
	}
}
