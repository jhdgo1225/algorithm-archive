#include <iostream>
#include <cmath>
using namespace std;

int arr[50001];
void func(int n)
{
	if (n == 0 || arr[n]) return ;
	for (int i=int(sqrt(n)); i>0; i--)
	{
		func(n - i * i);
		if (arr[n] == 0 || (arr[n] != 0 && arr[n] > arr[n - i * i] + 1))
			arr[n] = arr[n - i * i] + 1;
	}
}
int main()
{
	int n; cin >> n; func(n);
	cout << arr[n];
}