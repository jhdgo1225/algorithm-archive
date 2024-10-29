#include <iostream>
#include <stack>
using namespace std;

int main()
{
	stack<int> st;
	int n, k, cur = 1;
	string str;
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> k;
		while(cur <= k)
		{
			st.push(cur++);
			str += "+\n";
		}
		if (st.top() < k) {
			cout << "NO";
			return (0);
		}
		st.pop();
		str += "-\n";
	}
	cout << str;
}
