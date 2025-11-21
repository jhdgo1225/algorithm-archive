#include <iostream>
#include <queue>
using namespace std;
using ll = long long;
 
int main()
{
	freopen("./inputs/19113_input.txt", "r", stdin);
    int T; cin >> T;
    for (int t = 1; t <= T; t++) {
        int n; cin >> n;
        queue<ll> q;
        cout << "#" << t << ' ';
        for (int i = 0; i < n * 2; i++) {
            ll ret; cin >> ret;
            if (q.empty() || ret != q.front() * 4 / 3) q.push(ret);
            else {
                cout << q.front() << ' ';
                q.pop();
            }
        }
        cout << '\n';
    }
    return 0;
}