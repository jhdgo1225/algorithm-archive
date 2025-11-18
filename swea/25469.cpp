#include<iostream>

using namespace std;
using ull = unsigned long long;

int main()
{
	int test_case, T;
	freopen("./input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int a, b; cin>>a>>b;
        char board[51][51];
		ull ua[51]={0,}, ub[51]={0,};
        for (int i=0; i<a; i++) {
            for (int j=0; j<b; j++) {
				cin >> board[i][j];
				if (board[i][j] == '#') {
					ub[j] |= ((ull)1 << i);
					ua[i] |= ((ull)1 << j);
				}
			}
		}
		int cnt1=0,cnt2=0;
		for (int i=0; i<a; i++)
			if (ua[i] == ((ull)1 << b) - 1)
				cnt1++;
		for (int j=0; j<b; j++)
			if (ub[j] == ((ull)1 << a) - 1)
				cnt2++;
		if (cnt1 == a && cnt2 == b) cout << min(cnt1, cnt2);
		else cout << cnt1 + cnt2;
		cout << '\n';
	}
	return 0;
}
