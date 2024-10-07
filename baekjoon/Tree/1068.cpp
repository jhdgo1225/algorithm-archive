// BOJ 1068번: 트리
#include <iostream>
#include <vector>
using namespace std;

vector<int> graphs[51];
int p[51], res;

void delete_sub(int node)
{
	if (graphs[node].empty())
		return ;
	for (auto nxt : graphs[node])
		delete_sub(nxt);
	graphs[node].clear();
}

void find_leaf(int root)
{
	if (graphs[root].empty())
	{
		res += 1;
		return ;
	}
	for (auto nxt : graphs[root])
		find_leaf(nxt);
}

int main()
{
	int n, d, root;
	cin >> n;
	for (int i=0; i<n; i++)
	{
		cin >> p[i];
		if (p[i] == -1) root = i;
		else graphs[p[i]].push_back(i);
	}
	cin >> d;
	graphs[p[d]].erase(remove(graphs[p[d]].begin(), graphs[p[d]].end(), d), graphs[p[d]].end());
	delete_sub(d);
	if (d != root)
		find_leaf(root);
	cout << res;
}