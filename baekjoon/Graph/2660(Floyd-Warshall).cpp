// Baekjoon 2660번: 회장뽑기 Floyd-Warshall Version
#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
	int g[50][50];
	int n;cin>>n;
	memset(g,0x3f,n*sizeof g[0]);
	for(int i=0;i<n;++i)g[i][i]=0;
	int a,b;
	while(cin>>a>>b && a>0 && b>0){
		g[a-1][b-1]=1;
		g[b-1][a-1]=1;
	}
	for(int k=0;k<n;++k)
		for(int i=0;i<n;++i)
			for(int j=0;j<n;++j)
				g[i][j] = min(g[i][j], g[i][k]+g[k][j]);
	for(int i=0;i<n;++i)
		g[0][i]=*max_element(g[i],g[i]+n);
	int mx=*min_element(g[0],g[0]+n);
	int c=count(g[0],g[0]+n,mx);
	cout << mx << ' ' << c << '\n';
	for(int i=0; i<n; ++i)
		if(g[0][i] == mx)
			cout << i + 1 << ' ';
	return 0;
}