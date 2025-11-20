#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
using namespace std;

int main()
{
	cin.tie(0);
    ios::sync_with_stdio(0);
	int N, M; cin >> N >> M;
	string person;
	vector<string> aNobody;
	set<string> notHeard;
	while (N--) {
		cin >> person;
		notHeard.insert(person);
	}
	while (M--) {
		cin >> person;
		if (notHeard.find(person) != notHeard.end()) aNobody.push_back(person);
	}
	cout << aNobody.size() << '\n';
	sort(aNobody.begin(), aNobody.end());
	for (auto it: aNobody) cout << it << '\n';
}