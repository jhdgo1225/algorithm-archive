#include <cstdio>
#define INF 0x3f3f3f3f
using namespace std;

int adj[401][401];
int main()
{
	int v, e, a, b, c;
	scanf("%d %d", &v, &e);
	for (int i=1; i<=v; i++) for (int j=1; j<=v; j++) adj[i][j] = INF;
	for (int i=0; i<e; i++)
	{
		scanf("%d %d %d", &a, &b, &c);
		adj[a][b] = c;
	}
	for (int k=1; k<=v; k++)
		for (int i=1; i<=v; i++)
			for (int j=1; j<=v; j++)
				if (adj[i][j] > adj[i][k] + adj[k][j])
					adj[i][j] = adj[i][k] + adj[k][j];
	int res = INF;
	for (int i=1; i<=v; i++)
		if (res > adj[i][i]) res = adj[i][i];
	if (res == INF) res = -1;
	printf("%d", res);
}