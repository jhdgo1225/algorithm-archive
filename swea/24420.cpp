#include<iostream>
#include <set>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	freopen("./inputs/24420_input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int a,b,tmp;
		set<int> sa, sb;
		cin >> a >> b;
		for (int i=0; i<a; i++) {
			cin >> tmp;
			sa.insert(tmp);
		}
		for (int i=0; i<b; i++) {
			cin >> tmp;
			sb.insert(tmp);
		}
		set<int> min_set, max_set; char ch;
		if (sa.size() < sb.size()) {
			min_set = sa;
			max_set = sb;
			ch = '<';
		} else {
			min_set = sb;
			max_set = sa;
			ch = '>';
		}
		int cnt = 0;
		for (auto it: min_set) {
			if (max_set.find(it) != max_set.end())
				cnt++;
		}
		if (min_set.size() != cnt) cout << '?';
		else if (cnt == max_set.size()) cout << '=';
		else cout << ch;
		cout << '\n';
	}
	return 0;
}