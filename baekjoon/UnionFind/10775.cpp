#include <iostream>
using namespace std;

int group[100001];
int arr[100001];

int find(int x) {
	if (group[x] < 0) {
		group[x] = x;
		return x;
	}
	if (x == 0) return 0;
	return group[x] = find(group[x] - 1);
}

int main()
{
	int G, P, g, i; cin >> G >> P;
	fill(group + 1, group + 1 + G, -1);
	for (i=0; i<P; i++) cin >> arr[i];
	for (i=0; i<P; i++) {
		find(arr[i]);
		if (group[arr[i]] == 0) break;
	}
	cout << i;
}