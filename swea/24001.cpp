#include <iostream>
#include <cmath>
using namespace std;

int func_24001(string& str, char ch)
{
	int res = 0, maximum_dist=0;
	for (int i=0; i<str.size(); i++) {
		if (str[i] == 'L' || (str[i] == '?' && ch == 'L')) res--;
		else res++;
		maximum_dist = max(maximum_dist, abs(res));
	}
	return maximum_dist;
}

int main()
{
	int test_case;
	int T;
	freopen("./inputs/24001_input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
		string str; cin >> str;
		cout << max(func_24001(str, 'L'), func_24001(str, 'R')) << '\n';
	}
	return 0;
}