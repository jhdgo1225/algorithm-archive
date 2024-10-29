#include <iostream>
#include <algorithm>
#include <deque>
using namespace std;

int main()
{
	deque<int> q;
	int t; cin >> t;
	while (t--)
	{
		q.clear();
		string str, arr;
		cin >> str;
		int n; cin >> n;
		cin >> arr;
		int sum = 0;
		for (auto c : arr) {
			if (c == '[' || c == ']') continue;
			if (c >= '0' && c <= '9') sum = sum * 10 + c - '0';
			else {
				if (sum > 0) q.push_back(sum);
				sum = 0;
			}
		}
		if (sum) q.push_back(sum);
		bool flag=false, errFlag=false;
		for (int i=0; i<str.size(); i++)
		{
			if (str[i] == 'R') flag = !flag;
			else {
				if (q.empty()) {
					errFlag = true;
					break;
				}
				if (flag) q.pop_back();
				else q.pop_front();
			}
		}
		if (errFlag)
		{
			cout << "error\n";
			continue;
		}
		cout << '[';
		if (flag) reverse(q.begin(), q.end());
		for (int i=0; i<q.size(); i++)
		{
			cout << q[i];
			if (i < q.size() - 1) cout << ",";
		}
		cout << "]\n";
	}
}