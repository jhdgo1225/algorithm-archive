#include <iostream>
using namespace std;
using ll = long long;

struct point {
	ll x, y;
	bool operator<=(point &p1) {
		if (x == p1.x)
			return y <= p1.y;
		return x <= p1.x;
	}
};

struct line {
	point p1, p2;
};

int ccw(point &p1, point &p2, point &p3) {
	ll res = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
	if (res > 0) return 1;
	if (res < 0) return -1;
	return 0;
}

bool lineIntersect(line &l1, line &l2) {
	int chk1 = ccw(l1.p1, l1.p2, l2.p1) * ccw(l1.p1, l1.p2, l2.p2);
	int chk2 = ccw(l2.p1, l2.p2, l1.p1) * ccw(l2.p1, l2.p2, l1.p2);
    if (chk1 == 0 && chk2 == 0) {
        if (l1.p2 <= l1.p1) swap(l1.p1, l1.p2);
        if (l2.p2 <= l2.p1) swap(l2.p1, l2.p2);
        return (l1.p1 <= l2.p2 && l2.p1 <= l1.p2);
    }
	return (chk1 <= 0 && chk2 <= 0);
}

int main()
{
	line l1, l2;
	cin >> l1.p1.x >> l1.p1.y >> l1.p2.x >> l1.p2.y;
	cin >> l2.p1.x >> l2.p1.y >> l2.p2.x >> l2.p2.y;
	cout << lineIntersect(l1, l2);
}