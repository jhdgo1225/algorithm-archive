#include <iostream>
#include <algorithm>
using namespace std;

int arr[51];
int sorting[51];
bool chk[51];
int main()
{
	int N; cin >> N;
	for (int i=0; i<N; i++)
	{
		cin >> arr[i];
		sorting[i] = arr[i];
	}
	sort(sorting, sorting + N);
	for (int i=0; i<N; i++)
	{
		int idx = lower_bound(sorting, sorting + N, arr[i]) - sorting;
		while (chk[idx]) idx++;
		cout << idx << ' '; chk[idx] = true;
	}
}