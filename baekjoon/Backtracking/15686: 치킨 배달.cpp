#include <iostream>
#include <vector>
using namespace std;

int n, m, res = 0x3f3f3f3f;
vector<pair<int, int> > house;
vector<pair<int, int> > chicken;
vector<pair<int, int> > selected;

void func(int depth, int start)
{
	if (depth == m)
	{
		int tmp = 0;
		for (auto iter=house.begin(); iter != house.end(); iter++)
		{
			int chickenDist = 0x3f3f3f3f;
			for (auto iter2=selected.begin(); iter2 != selected.end(); iter2++)
				chickenDist = min(chickenDist, abs(iter->first - iter2->first) + abs(iter->second - iter2->second));
			tmp += chickenDist;
		}
		res = min(res, tmp);
	}
	for (int i=start; i<chicken.size(); i++)
	{
		selected.push_back(chicken[i]);
		func(depth + 1, i + 1);
		selected.pop_back();
	}
}

int main(void)
{
	int info;
	cin >> n >> m;
	for (int i=0; i<n; i++)
	{
		for (int j=0; j<n; j++)
		{
			cin >> info;
			if (info == 1) house.push_back({i, j});
			if (info == 2) chicken.push_back({i, j});
		}
	}
	func(0, 0);
	cout << res;
}