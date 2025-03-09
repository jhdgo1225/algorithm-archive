#include <iostream>
using namespace std;

struct point
{
	long long x;
	long long y;
};

struct line
{
	point p1;
	point p2;
};

int ccw(point p1, point p2, point p3) {
    long long chk = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
	if (chk > 0) return 1;
	if (chk < 0) return -1;
	return 0;
}

bool intersect_line(line& l1, line& l2) {
	int case1 = ccw(l1.p1, l2.p1, l1.p2);
	int case2 = ccw(l1.p1, l2.p2, l1.p2);
	int case3 = ccw(l2.p1, l1.p1, l2.p2);
	int case4 = ccw(l2.p1, l1.p2, l2.p2);
	int chk1 = case1 != case2;
	int chk2 = case3 != case4;
	return (chk1 && chk2);
}

int main()
{
	line l1, l2;
	cin >> l1.p1.x >> l1.p1.y >> l1.p2.x >> l1.p2.y;
	cin >> l2.p1.x >> l2.p1.y >> l2.p2.x >> l2.p2.y;
	cout << intersect_line(l1, l2);
}