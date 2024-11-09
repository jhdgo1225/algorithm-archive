#include <iostream>
using namespace std;

int m, n, board[101][101];
int cnt;
void tiltDown(int col)
{
	int idx = m-1;
	for (int row=m-1; row>=0; row--)
	{
		if (!board[row][col]) continue;
		if (board[idx][col]) idx--;
		else if (board[row][col]) {
			board[idx][col] = board[row][col];
			board[row][col] = 0;
			cnt += (idx-- - row);
		}
	}
}
int main()
{
	int t;cin>>t;
	while (t--)
	{
		cin >> m >> n;
		for (int i=0; i<m; i++)
			for (int j=0; j<n; j++)
				cin >> board[i][j];
		for (int i=0; i<n; i++)
			tiltDown(i);
		cout << cnt << '\n';
		cnt = 0;
	}
}