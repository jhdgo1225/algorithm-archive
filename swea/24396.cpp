#include <iostream>
using namespace std;

int main()
{
	freopen("./24396_input.txt", "r", stdin);
	int T;
	cin >> T;
	for (int i=0; i<T; i++)
	{
		int B,W,X,Y,Z,res=-200000;
		cin >> B >> W >> X >> Y >> Z;
		res = max(res, B*X+W*Y);
		if (B > W) res = max(res, 2*W*Z+(B-W)*X);
		if (B < W) res = max(res, 2*B*Z+(W-B)*Y);
		if (B == W) res = max(res, (B+W)*Z);
		cout << res << '\n';
	}
	return 0;
}
