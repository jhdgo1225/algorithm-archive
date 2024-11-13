#include <iostream>
#include <algorithm>
using namespace std;

int a[1000001];
int b[1000001];
int idx[1000001];
int main()
{
	int mm = -1;
	int n; cin >> n;
	for (int i=0; i<n; i++)
		cin >> a[i];
	for (int i=0; i<n; i++)
	{
		idx[i] = (int)(lower_bound(b, b + mm + 1, a[i]) - b);
		b[idx[i]] = a[i];
		mm = max(mm, idx[i]);
	}
	int k=mm;
	cout << mm + 1 << '\n';
	for (int i=n-1; i>=0; i--)
		if (mm == idx[i]) 
			b[mm--] = a[i];
	for (int i=0; i<=k; i++)
		cout << b[i] << ' ';
}