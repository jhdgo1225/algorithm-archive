#include <iostream>
using namespace std;

bool palindrome(string target, int s, int e)
{
	int idxStart = s, idxEnd = e;
	while (idxStart < (s + e) / 2) {
		if (target[idxStart] != target[idxEnd - 1]) return false;
		idxStart++; idxEnd--;
	}
	return true;
}

int main()
{
	freopen("./inputs/20019_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		string str; cin >> str;
		cout << '#' << i + 1 << ' ';
		if (palindrome(str, 0, str.size()) && palindrome(str, 0, (str.size() - 1)/2) && palindrome(str, str.size() / 2 + 1, str.size())) cout << "YES\n";
		else cout << "NO\n";
	}
}