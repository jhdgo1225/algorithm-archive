#include <iostream>
using namespace std;

bool palindrome(string str)
{
	int s = 0, e = str.size();
	while (s < (s + e) / 2) {
		if (str[s] != str[e - s - 1]) return false;
		s++;
	}
	return true;
}

int main()
{
	freopen("./inputs/19003_input.txt", "r", stdin);
	int T;cin>>T;
	for (int testcase=0; testcase<T; testcase++) {
		string str[101];
		int N, M, pair[101] = {0, };
		bool pairChecked[101] = {false,};
		cin >> N >> M;
		for (int i=0; i<N; i++) {
			cin >> str[i];
		}
		for (int i=0; i<N-1; i++) {
			for (int j=i+1; j<N; j++) {
				if (palindrome(str[i] + str[j]) && pair[i] == 0 && pair[j] == 0) {
					pair[i] = j + 1;
					pair[j] = i + 1;
				}
			}
		}
		int cnt = 0;
		for (int i=0; i<N; i++) {
			if (pair[i] > 0 && !pairChecked[i]) {
				cnt += 2;
				pairChecked[i] = pairChecked[pair[i] - 1] = true;
			}
		}
		for (int i=0; i<N; i++)
		{
			if (palindrome(str[i]) && !pairChecked[i])
			{
				cnt += 1;
				break;
			}
		}
		cout << "#" << testcase + 1 << ' ';
		cout << cnt * M << '\n';
	}
}