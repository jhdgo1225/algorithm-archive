#include <iostream>
#include <map>
using namespace std;

map<string, string> to = {{"a", "as"},
	{"i", "ios"},
	{"y", "ios"},
	{"l", "les"},
	{"n", "anes"},
	{"ne", "anes"},
	{"o", "os"},
	{"r", "res"},
	{"t", "tas"},
	{"u", "us"},
	{"v", "ves"},
	{"w", "was"}};

int main()
{
	int T;cin>>T;
	for(int i=0; i<T; i++)
	{
		string s, x; cin>>s;
		if ((x = to[s.substr(s.size()-1)]) != "")
			cout<<s.substr(0, s.size()-1) + x<<'\n';
		else if (s.size() >= 2 && (x = to[s.substr(s.size()-2)]) != "")
			cout<<s.substr(0, s.size()-2) + x<<'\n';
		else
			cout<<s+"us"<<'\n';
	}
}