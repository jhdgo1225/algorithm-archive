#include <iostream>
using namespace std;

int N, L, cnt;
int board[101][101];
bool visited[101][101];

bool chk(int row, int col, int type)
{
	int tmp, tmpL = L;
	if (type == 1 || type == 2) tmp = col;
	else tmp = row;
	while (tmpL--)
	{
		if (type == 1 || type == 2) {
			if (tmp <= 0 || tmp > N || visited[row][tmp] || board[row][col] != board[row][tmp])
				return false;
			visited[row][tmp] = true;
		}
		else {
			if (tmp <= 0 || tmp > N || visited[tmp][col] || board[row][col] != board[tmp][col])
				return false;
			visited[tmp][col] = true;
		}
		if (type == 1 || type == 3) tmp--;
		else tmp++;
	}
	return true;
}

int main()
{
	cin >> N >> L;
	for (int i=1; i<=N; i++)
		for (int j=1; j<=N; j++)
			cin >> board[i][j];
	for (int i=1; i<=N; i++)
	{
		int idx = 1;
		bool flag = true;
		while (idx < N)
		{
			if (board[i][idx] - board[i][idx + 1] > 1 || board[i][idx] - board[i][idx + 1] < -1)
			{
				flag = false;
				break;
			}
			if (board[i][idx] < board[i][idx + 1]) {
				flag = chk(i, idx, 1);
				idx++;
			}
			else if (board[i][idx] > board[i][idx + 1]) {
				flag = chk(i, idx + 1, 2);
				idx += L;
			}
			else idx++;
			if (!flag) break;
		}
		if (!flag) continue;
		cnt++;
	}
	for (int i=1; i<=N; i++)
		fill(visited[i], visited[i] + N + 1, false);
	for (int j=1; j<=N; j++)
	{
		int idx = 1;
		bool flag = true;
		while (idx < N)
		{
			if (board[idx][j] - board[idx + 1][j] > 1 || board[idx][j] - board[idx + 1][j] < -1)
			{
				flag = false;
				break;
			}
			if (board[idx][j] < board[idx + 1][j]) {
				flag = chk(idx, j, 3);
				idx++;
			}
			else if (board[idx][j] > board[idx + 1][j]) {
				flag = chk(idx + 1, j, 4);
				idx += L;
			}
			else idx++;
			if (!flag) break;
		}
		if (!flag) continue;
		cnt++;
	}
	cout << cnt;
}