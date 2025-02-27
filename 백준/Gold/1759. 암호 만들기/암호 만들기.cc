#include <bits/stdc++.h>
using namespace std;

char passwd[16];
int isused[16];
int main()
{
	int n, c;
	int ja, mo;
	cin >> n >> c;
	for (int i=0; i<c; i++)
		cin >> passwd[i];
	fill(isused, isused + 16, 1);
	fill(isused, isused + n, 0);
	sort(passwd, passwd + c);
	do {
		ja = mo = 0;
		for (int i=0; i<c; i++)
		{
			if (isused[i] == 0)
			{
				if (passwd[i] == 'a' || passwd[i] == 'e' || passwd[i] == 'i'
					|| passwd[i] == 'o' || passwd[i] == 'u')
					mo += 1;
				else
					ja += 1;
			}
		}
		if (mo < 1 || ja < 2)
			continue;
		for (int i=0; i<c; i++)
			if (isused[i] == 0)
				cout << passwd[i];
		cout << '\n';
	} while (next_permutation(isused, isused + c));
	return 0;
}
