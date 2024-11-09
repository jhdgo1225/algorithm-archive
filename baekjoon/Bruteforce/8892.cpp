#include <iostream>
#include <deque>
using namespace std;

string words[101];
bool visited[101];
deque<string> q;

bool dfs(int n, int depth, int limit)
{
	if (depth == limit)
	{
		bool flag = true;
		string tmp = q[0] + q[1];
		for (int i=0; i<tmp.size()/2; i++)
		{
			if (tmp[i] != tmp[tmp.size() - 1 - i])
			{
				flag = false;
				break;
			}
		}
		if (flag) cout << tmp << '\n';
		return (flag);
	}
	for (int i=0; i<n; i++)
	{
		if (visited[i]) continue;
		visited[i] = true;
		q.push_back(words[i]);
		if (dfs(n, depth + 1, limit)) {
			q.pop_back();
			visited[i] = false;
			return true;
		}
		q.pop_back();
		visited[i] = false;
	}
	return (false);
}

int main()
{
	int T;cin>>T;
	while (T--)
	{
		int n; cin >> n;
		for (int i=0; i<n; i++)
			cin >> words[i];
		if (dfs(n, 0, 2)) continue;
		cout << "0\n";
	}
}