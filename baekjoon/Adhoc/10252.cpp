#include <iostream>
using namespace std;

int main()
{
	int t; cin >> t;
	while(t--){
		int n, m; cin >> n >> m;
		cout << 1 << "\n";
		for(int j=0; j<m; j++) cout << "(" << 0 << "," << j << ")\n";
		for (int j=m-1; j>=0; j--)
			for (int i=1; i<n; i++)
				cout << "(" << ((m-j)%2 ? i : n-i) << "," << j << ")\n";
	}
}
