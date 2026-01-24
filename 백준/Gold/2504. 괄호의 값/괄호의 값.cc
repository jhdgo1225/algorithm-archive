#include <iostream>
#include <stack>

using namespace std;

int main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int flag = 0;
	int res = 0;
	int tmp;
	int tmp2;
	char str[31];
	stack<char> s;
	stack<int> num;
	cin >> str;
	for (int i=0; str[i] != 0; i++) {
		if (str[i] == '(' || str[i] == '[') {
			tmp = 1;
			s.push(str[i]);
		} else {
			tmp2 = 0;
			while (!s.empty() && s.top() == '.') {
				tmp2 += num.top();
				s.pop();
				num.pop();
			}
			if (s.empty()) {
				flag = 1;
				break;
			}
			if (tmp2 > 0)
				tmp = tmp2;
			if (str[i] == ')' && s.top() == '(')
				tmp *= 2;
			else if (str[i] == ']' && s.top() == '[')
				tmp *= 3;
			else {
				flag = 1;
				break;
			}
			num.push(tmp);
			s.pop();
			s.push('.');
		}
	}
	while (!s.empty() && s.top() == '.') {
		res += num.top();
		num.pop();
		s.pop();
	}
	if (flag || !s.empty())
		cout << 0;
	else
		cout << res;
	return 0;
}