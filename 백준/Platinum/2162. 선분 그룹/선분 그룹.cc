#include <iostream>
#include <map>
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

int group[3001];
line lines[3001];

int find(int node) {
	if (group[node] < 0) return (node);
	return group[node] = find(group[node]);
}

void uni(int a, int b) {
	a = find(a);
	b = find(b);
	if (a == b) return ;
	if (group[a] > group[b]) swap(a, b);
	if (group[a] == group[b]) group[a]--;
	group[b] = a;
}

map<int, int> members;

int main()
{
	int N, res = 0; cin >> N;
	for (int i=1; i<=N; i++)
		cin >> lines[i].p1.x >> lines[i].p1.y >> lines[i].p2.x >> lines[i].p2.y;
	fill(group + 1, group + N + 1, -1);
	for (int i=1; i<=N-1; i++) {
		for (int j=i+1; j<=N; j++) {
			if (lineIntersect(lines[i], lines[j])) uni(i, j);
		}
	}
	for (int i=1; i<=N; i++) {
		int tmp = ++members[find(i)];
		res = max(res, tmp);
	}
	cout << members.size() << '\n' << res;
}