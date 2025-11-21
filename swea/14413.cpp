#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/14413_input.txt", "r", stdin);
    int T; cin >> T;
	int dx[4] = {-1, 1, 0, 0};
	int dy[4] = {0, 0, -1, 1};
    for (int t = 1; t <= T; t++) {
        int N, M; cin >> N >> M;
		char board[51][51];
		bool isOK = true;
		for (int i=0; i<N; i++) for (int j=0; j<M; j++) cin >> board[i][j];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j] != '?') {
					for (int k=0; k<4; k++) {
						if (i + dx[k] < 0 || i + dx[k] >= N || j + dy[k] < 0 || j + dy[k] >= M) continue; 
						if (board[i][j] == board[i + dx[k]][j + dy[k]]) {
							isOK = false;
						}
						if (board[i + dx[k]][j + dy[k]] == '?') {
							if (board[i][j] == '.') board[i + dx[k]][j + dy[k]] = '#';
							else board[i + dx[k]][j + dy[k]] = '.';
						}
					}
				}
			}
			if (!isOK) break;
		}
        cout << "#" << t << ' ' << (isOK ? "possible" : "impossible") << '\n';
    }
    return 0;
}