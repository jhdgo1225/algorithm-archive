#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
	int a[51];
	int N; cin >> N;
	for (int i=0; i<N; i++) cin >> a[i];
	cout << (*min_element(a, a+N)) * (*max_element(a, a+N));
}
