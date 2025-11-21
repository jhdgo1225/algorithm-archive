#include <iostream>
#include <deque>
using namespace std;

int main()
{
	freopen("./inputs/20955_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		string a,b; cin >> a >> b;
		deque<char> leading, following;
		bool isOK = true, rev = false;
		for (int i=0; i<a.size(); i++) leading.push_back(a[i]);
		for (int i=0; i<b.size(); i++) following.push_back(b[i]);
		while (leading.size() < following.size()) {
			char chk;
			if (rev) {
				chk = following.front();
				following.pop_front();
			}
			else {
				chk = following.back();
				following.pop_back();
			}
			if (chk == 'Y') rev = true;
		}
		for (int leadingIdx=0; leadingIdx<leading.size(); leadingIdx++) {
			int followingIdx;
			if (rev) followingIdx = following.size() - leadingIdx - 1;
			else followingIdx = leadingIdx;
			if (leading[leadingIdx] != following[followingIdx]) {
				isOK = false;
				break;
			}
		}
        cout << "#" << i + 1 << ' ';
		cout << (isOK ? "Yes\n" : "No\n");
	}
}
