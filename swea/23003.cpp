#include <iostream>
#include <map>
using namespace std;

int main()
{
	map<string, int> colorSpace = {
		{"red", 0},
		{"orange", 1},
		{"yellow", 2},
		{"green", 3},
		{"blue", 4},
		{"purple", 5},
	};
	freopen("./23003_input.txt", "r", stdin);
	int T; cin >> T;
	for (int i=0; i<T; i++)
	{
		string color1, color2;
		cin >> color1 >> color2;
		int color1Idx = colorSpace[color1];
		int color2Idx = colorSpace[color2];
		if (color1Idx == color2Idx) cout << "E";
		else if ((color1Idx + 1) % 6 == color2Idx || (6 * (color1Idx < 1) + (color1Idx - 1)) == color2Idx) cout << "A";
		else if (color1Idx == (color2Idx + 3) % 6) cout << "C";
		else cout << "X";
		cout << '\n';
	}
}