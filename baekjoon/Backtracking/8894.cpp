#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[10];
vector<int> arr;
int patternCnt;
int oneMove[10] = {-5, -4, -3, -2, -1, 1, 2, 3, 4, 5};
int twoMove[8] = {-8, -6, -4, -2, 2, 4, 6, 8};
bool visited[10];
void input()
{
	int N, s, d; cin >> N;
	for (int i=1; i<=9; i++)
		graphs[i].clear();
	fill(visited, visited + 10, true);
	while (N--)
	{
		cin >> s >> d;
		visited[s] = visited[d] = false;
	}
	for (int i=1; i<=9; i++)
	{
		if (!visited[i])
		{
			for (int j=1; j<=9; j++)
				if (i != j && !visited[j])
					graphs[i].push_back(j);
			patternCnt++;
		} 
	}
}

bool dfs(int start)
{
	arr.push_back(start);
	for (auto nxt:arr) cout << nxt << ' ';
	if (patternCnt == arr.size())
	{
		for (auto nxt:arr) cout << nxt << ' ';
		cout << '\n';
		return (true);
	}
	cout << ",cur number: " << start << ' ' << graphs[start].size() << '\n';
	for (int i=0; i<graphs[start].size(); i++)
	{
		for (int j=0; j<10; j++)
		{
			if (((oneMove[j] < 0 && start + oneMove[j] >= 1) || (oneMove[j] > 0 && start + oneMove[j] <= 9))
				&& graphs[start][i] == start + oneMove[j]
				&& !visited[start + oneMove[j]])
			{
				visited[start + oneMove[j]] = true;
				if (dfs(start + oneMove[j]))
					return (true);
				visited[start + oneMove[j]] = false;
			}
		}
		for (int j=0; j<8; j++)
		{
			if (((twoMove[j] < 0 && start + twoMove[j] >= 1) || (twoMove[j] > 0 && start + twoMove[j] <= 9))
				&& graphs[start][i] == start + twoMove[j]
				&& !visited[start + twoMove[j]])
			{
				bool flag = false;
				cout << start << ' ' << start + twoMove[j] << '\n';
				if (!visited[start + twoMove[j] / 2]) flag = true;
				if (flag) {
					visited[start + twoMove[j] / 2] = true;
					arr.push_back(start + twoMove[j] / 2);
				}
				visited[start + twoMove[j]] = true;
				if (dfs(start + twoMove[j]))
					return (true);
				visited[start + twoMove[j]] = false;
				if (flag) {
					visited[start + twoMove[j] / 2] = false;
					arr.pop_back();
				}
			}
		}
	}
	arr.pop_back();
	return (false);
}

int main()
{
	int T, N, s, d; cin >> T;
	while (T--)
	{
		input();
		bool flag = false;
		cout << patternCnt << '\n';
		for (int i=1; i<=9; i++)
		{
			if (visited[i]) continue;
			visited[i] = true;
			flag = dfs(i);
			if (flag) break;
			visited[i] = false;
		}
		if (!flag) cout << "IMPOSSIBLE\n";
	}
}