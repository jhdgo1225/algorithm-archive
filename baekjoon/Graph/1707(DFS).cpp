// 참조 링크: https://www.acmicpc.net/source/50625246
// Baekjoon 1707번: 이분 그래프 DFS Version
#include <iostream>
#include <vector>
using namespace std;

const int M = 20020;
vector<int> graphs[M];
int color[M], ans;

void dfs(int x,int c){
	color[x] = 1+c;
	for(auto i : graphs[x]) {
		if(color[i]==0) dfs(i,!c);
		else if(color[i]==color[x]) ans = 0;
	}
}

int main() {
	int tc;
	cin >> tc;
	while (tc--){
		int n, m; cin >> n >> m;
		for(int i = 0;i<m;i++){
			int a, b;
			cin >> a >> b;
			graphs[a].push_back(b);
			graphs[b].push_back(a);
		}
		ans = 1;
		for(int i=1; i<=n; i++)
			if(!color[i])
				dfs(i,0);
		cout << (ans?"YES":"NO") << '\n';
		for(int i = 1;i<=n;i++) {
			color[i] = 0;
			graphs[i].clear();
		}
	}
}
