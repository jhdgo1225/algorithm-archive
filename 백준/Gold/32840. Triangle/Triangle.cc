#include <iostream>
#include <numeric>
#include <cmath>
#include <climits>
#include <deque>
using namespace std;
using ll = long long;

struct coordinate {
	ll x;
	ll y;
};

coordinate avail[3][2];
ll minRes = LONG_MAX;
ll maxRes = LONG_MIN;
deque<coordinate> q;

void calcArea()
{
	ll area = abs((q[1].x - q[0].x) * (q[2].y - q[0].y) - (q[2].x - q[0].x) * (q[1].y - q[0].y));
	minRes = min(minRes, area);
	maxRes = max(maxRes, area);
}

void dfs(int depth)
{
	if (depth == 3) {
		calcArea();
		return ;
	}
	for (int i=0; i<2; i++) {
		q.push_back(avail[depth][i]);
		dfs(depth + 1);
		q.pop_back();
	}
}

int main()
{
	coordinate coords[3];
	for (int i=0; i<3; i++) cin >> coords[i].x >> coords[i].y;
	for (int i=0; i<3; i++) {
		ll diffX = coords[(i + 1) % 3].x - coords[i].x;
		ll diffY = coords[(i + 1) % 3].y - coords[i].y;
		ll gcdTwoCoords = gcd(abs(diffX), abs(diffY));
		if (gcdTwoCoords == 1) {
			cout << -1;
			return (0);
		}
		ll gapCoordsX = diffX / gcdTwoCoords;
		ll gapCoordsY = diffY / gcdTwoCoords;
		avail[i][0].x = coords[i].x + gapCoordsX;
		avail[i][0].y = coords[i].y + gapCoordsY;
		avail[i][1].x = coords[(i + 1) % 3].x - gapCoordsX;
		avail[i][1].y = coords[(i + 1) % 3].y - gapCoordsY;
	}
	dfs(0);
	cout << maxRes << ' ' << minRes;
}