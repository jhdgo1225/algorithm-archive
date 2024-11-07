#include <iostream>
using namespace std;

int ladder[31][11];
int n,m,h;

bool isManipulated() {
	for (int j=1; j<=n; j++)
	{
		int col = j;
		for(int i=1;i<=h;++i) {
			if(ladder[i][col]) col++;
			else if(ladder[i][col-1]) col--;
		}
		if(col != j)
			return false;
	}
	return true;
}

void dfs(int depth, int limit) {
	if(depth == limit) {
		if(isManipulated()) {
			cout << limit;
			exit(0);
		}
		return;
	}
	for(int j=1;j<n;++j)
	{
		for(int i=1;i<=h;++i)
		{
			if(ladder[i][j] || ladder[i][j-1] || ladder[i][j+1]) continue;
			ladder[i][j] = 1;
			dfs(depth+1, limit);
			ladder[i][j] = 0;
			while(!ladder[i][j-1] && !ladder[i][j+1]) i++;
		}
	}
}

int main(void) {
	cin >> n >> m >> h;
	for(int i=1;i<=m;++i) {
		int a,b;
		cin >> a >> b;
		ladder[a][b] = 1;
	}
	for(int i=0;i<=3;i++)
		dfs(0, i);
	cout << -1;
	return 0;
}
