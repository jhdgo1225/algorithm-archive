#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	freopen("./inputs/23791_input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int N; cin >> N;
		int a[51] = {0, }, b[51] = {0, };
		char choices[51] = {0, };
		for (int i=0; i<N; i++) cin >> a[i];
		for (int i=0; i<N; i++) cin >> b[i];
		int aIdx = 0, bIdx = 0;
		while (aIdx != N || bIdx != N) {
			while (aIdx < N && choices[a[aIdx]] != 0) aIdx++;
			if (aIdx < N && choices[a[aIdx]] == 0) choices[a[aIdx++]] = 'A';
			while (bIdx < N && choices[b[bIdx]] != 0) bIdx++;
			if (bIdx < N && choices[b[bIdx]] == 0) choices[b[bIdx++]] = 'B';
		}
		for (int i=1; i<=N; i++) cout << choices[i];
		cout << '\n';
	}
	return 0;
}