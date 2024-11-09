#include <iostream>
using namespace std;

string convert(string str, char ch, int pos)
{
	string ret;
	if (ch == 'a') ret = str.replace(pos, 1, "as");
	else if (ch == 'i' || ch == 'y') ret = str.replace(pos, 1, "ios");
	else if (ch == 'l') ret = str.replace(pos, 1, "les");
	else if (ch == 'n' || (str[pos-1] == 'n' && ch == 'e')) ret = str.replace(pos - (ch == 'e'), 1 + (ch == 'e'), "anes");
	else if (ch == 'o') ret = str.replace(pos, 1, "os");
	else if (ch == 'r') ret = str.replace(pos, 1, "res");
	else if (ch == 't') ret = str.replace(pos, 1, "tas");
	else if (ch == 'u') ret = str.replace(pos, 1, "us");
	else if (ch == 'v') ret = str.replace(pos, 1, "ves");
	else if (ch == 'w') ret = str.replace(pos, 1, "was");
	else ret = str + "us";
	return (ret);
}

int main()
{
	int T;cin >> T;
	while (T--)
	{
		string str; cin >> str;
		cout << convert(str, str[str.size() - 1], str.size() - 1) << '\n';
	}
}