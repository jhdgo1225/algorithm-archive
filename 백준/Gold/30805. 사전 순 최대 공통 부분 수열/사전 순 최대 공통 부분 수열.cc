#include <iostream>
#include <deque>
using namespace std;

int A[101];
int B[101];
deque<pair<int, int>> dq;
int main()
{
	int N, M, i, j, k;
	cin >> N; for (int i=0; i<N; i++) cin >> A[i];
	cin >> M; for (int i=0; i<M; i++) cin >> B[i];
	for (i=0; i<N; i++)
	{
		for (j=0; j<M; j++)
		{
			if (A[i] == B[j]) {
				k = 0;
				bool flag = false;
				while (k < dq.size()) {
					if (dq[k].first < B[j] || (dq[k].first == B[j] && dq[k].second == j)) {
						if (dq[k].first < B[j]) flag = true;
						break;
					}
					k++;
				}
				if (flag) {
					if (k - 1 >= 0 && dq[k-1].second > j) continue;
					while (k != dq.size()) dq.pop_back();
					dq.push_back({B[j], j});
					break;
				}
				if (!dq.size() || dq.back().second < j) {
					dq.push_back({B[j], j});
					break;
				}
			}
		}
	}
	cout << dq.size() << '\n';
	for (int i=0; i<dq.size(); i++)
		cout << dq[i].first << ' ';
}