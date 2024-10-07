// BOJ 1991번: 트리 순회
#include <iostream>
using namespace std;
char lc[30];
char rc[30];
void preorder(int cur)
{
	cout << (char)(cur + 65);
	if (lc[cur] != 0) preorder(lc[cur] - 65);
	if (rc[cur] != 0) preorder(rc[cur] - 65);
}
void inorder(int cur)
{
	if (lc[cur] != 0) inorder(lc[cur] - 65);
	cout << (char)(cur + 65);
	if (rc[cur] != 0) inorder(rc[cur] - 65);
}
void postorder(int cur)
{
	if (lc[cur] != 0) postorder(lc[cur] - 65);
	if (rc[cur] != 0) postorder(rc[cur] - 65);
	cout << (char)(cur + 65);
}
int main()
{
	int n;
	char a, b, c;
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> a >> b >> c;
		if (b != '.') lc[a - 65] = b;
		if (c != '.') rc[a - 65] = c;
	}
	preorder(0);cout << endl;
	inorder(0);cout << endl;
	postorder(0);
}
