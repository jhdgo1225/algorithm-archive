#include<iostream>

using namespace std;

int main()
{
	int test_case;
	int T;
	freopen("input.txt", "r", stdin);
	cin>>T;
	for(test_case = 1; test_case <= T; ++test_case)
	{
		bool ok = true, already[10] = {false,};
		int numberStatus[10] = {0};
		string str; cin >> str;
		for (int i=0; i<str.length(); i++) {
			if (already[str[i]-48]) ok = false;
			if (numberStatus[str[i]-48] != 0) {
				if ((i + 1) - numberStatus[str[i]-48] - 1 != str[i]-48) ok = false;
				already[str[i]-48] = true;
				numberStatus[str[i]-48] = 0;
			} else {
				numberStatus[str[i]-48] = i + 1;
			}
		}
		for (int i=0; i<10; i++) if (numberStatus[i] != 0) ok = false;
		cout << ((ok) ? "yes\n" : "no\n");
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}