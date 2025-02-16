#include <iostream>
#include <algorithm>
using namespace std;

const int INF = 987654321;
int v_num, e_num;
int adj[201][201];
int dist[201][201];

double func(){
  double res = INF, long_time, spent, len;
  int edge_len;
  for(int start=1; start<=v_num; ++start){
    long_time = 0; 
    for(int from = 1; from<=v_num; ++from){
      for(int to =1; to<=v_num; ++to){
        edge_len = adj[from][to];
        if (edge_len != -1) {
          len = edge_len - (dist[start][to] - dist[start][from]);
          if (len > 0) {
            spent = (len/2) + dist[start][to];
            long_time = max(spent, long_time);
          }
        }
      }
    }
    if (long_time < res)
      res = long_time;
  }
  return res;
}

int main(){
  cin >> v_num >> e_num;
  for (int i=1; i<=v_num; i++)
  {
    fill(adj[i] + 1, adj[i] + v_num + 1, -1);
    fill(dist[i] + 1, dist[i] + v_num + 1, INF);
  }
  for(int i=1; i<=v_num ; ++i)
    dist[i][i] = 0;
  int v1, v2, edge;
  for(int i=0; i<e_num ; ++i){
    cin >> v1 >> v2 >> edge;
    if(adj[v1][v2] == -1 || adj[v1][v2] < edge){
      adj[v1][v2] = edge;
      adj[v2][v1] = edge;
    }
    if(dist[v1][v2] == INF || edge < dist[v1][v2]){
      dist[v1][v2] = edge;
      dist[v2][v1] = edge;
    }
  }
  for(int k=1; k<=v_num ; ++k)
    for(int i=1; i<=v_num ; ++i)
      for(int j=1; j<=v_num ; ++j)
        dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
  cout.precision(1);
  cout << fixed << func();
  return 0;
}