#include <iostream>
using namespace std;

int main()
{
	freopen("./inputs/15612_input.txt", "r", stdin);
	int T=0;cin>>T;
	for (int testcase=0; testcase<T; testcase++)
	{
		int rock = 0;
		char board[8][8];
		bool isOK = true, rowChk[8] = {false, }, colChk[8] = {false, };
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				cin >> board[i][j];
				if (board[i][j] == 'O') {
					if (rowChk[i] || colChk[j]) {
						isOK = false;
					}
					rowChk[i] = colChk[j] = true;
					rock++;
				}
			}
			if (!rowChk[i]) {
				isOK = false;
			}
		}
		if (rock != 8) isOK = false;
		cout << "#" << (testcase + 1) << ' ' << (isOK ? "yes\n" : "no\n");
	}
}