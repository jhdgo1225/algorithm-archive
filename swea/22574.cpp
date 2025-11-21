#include <iostream>
using namespace std;
 
int main()
{
	freopen("./inputs/22574_input.txt", "r", stdin);
	int T; cin >> T;
	while (T--)
	{
		int sum=0,N,P; cin >> N >> P;
		for (int i=1; i<=N; i++) {
			sum += i;
			if (sum == P) sum -= 1;
		}
		cout << sum << '\n';
	}
}