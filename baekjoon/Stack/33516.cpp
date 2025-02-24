#include <iostream>
#include <stack>
using namespace std;

int main()
{
	int N, idx=0, res=0; cin >> N;
	string str, skeep="skeep"; cin >> str;
	stack<int> st;
	for (int i=0; i<str.size(); i++)
	{
		if (skeep[idx] == str[i]) idx++;
		else {
			if (str[i] == 's') idx = 1;
			else idx = 0;
		}
		st.push(idx);
		while (!st.empty() && st.top() == 5) {
			for (int j=0; j<5; j++) st.pop();
			res++;
			if (!st.empty() && st.top() >= 1) {
				idx = st.top() + 1;
				st.push(st.top() + 1);
			}
			else {
				st.push(0);
				idx = 0;
			}
		}
	}
	cout << res;
}
