#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/21131_input.txt", "r", stdin);
	int T, board[65][65]; cin>>T;
	for (int i=0; i<T; i++) {
		int N, cnt=0; cin >> N;
		for (int i=0; i<N; i++)
			for (int j=0; j<N; j++)
				cin >> board[i][j];
		for (int i=N; i>0; i--) {
			if (board[0][i-1] != i) {
				for (int j=0; j<i; j++) {
					int x = board[0][j], y = board[j][0];
					x = x ^ y;
					y = x ^ y;
					x = x ^ y;
					board[0][j] = x;
					board[j][0] = y;
				}
				cnt++;
			}
		}
		cout << cnt << '\n';
	}
}