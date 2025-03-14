#include <iostream>
#include <queue>
 
using namespace std;
int n;
int graph[22][22];
int dx[] = {0, -1, 1, 0};
int dy[] = {-1, 0, 0, 1};
int sharkX, sharkY;
int result = 0;
int cnt = 0;
int sharkSize = 2;
bool stop = false;
bool eat = false;

void bfs(int a, int b, bool visited[][22]){
    queue<pair<pair<int, int>, int>> q;
    q.push({{a, b}, 0});
    visited[b][a] = true;
    int time = 0;
    while(!q.empty()){
        int x = q.front().first.first;
        int y = q.front().first.second;
        int curTime = q.front().second;

        if(graph[y][x] > 0 && graph[y][x] < sharkSize && time == curTime){
            if((sharkY > y) || (sharkY == y && sharkX > x)) {
                sharkY = y;
                sharkX = x;
                continue;
            }
        }
        q.pop();
        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if(nx>=0 && nx <n && ny>=0 && ny <n && !visited[ny][nx]) {
                if(graph[ny][nx] <= sharkSize) {
                    if(graph[ny][nx] > 0 && graph[ny][nx] < sharkSize && !eat){ //물고기를 먹을 수 있는 경우
                        eat = true;
                        sharkX = nx;
                        sharkY = ny;
                        time = curTime + 1;
                        result += time;
                    }else{
                        q.push({{nx, ny}, curTime + 1});
                        visited[ny][nx] = true;  
                    }
                }
            }
        }
    }
}

void input() {
	cin >> n;
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n;j++){
            cin >> graph[i][j];
            if(graph[i][j] == 9){
                sharkY = i;
                sharkX = j;
                graph[i][j] = 0;
            }
        }
    }
}

void simulation() {
	while(!stop){
        bool visited[22][22] = {0};
        bfs(sharkX, sharkY, visited);
        if(eat) {
            eat = false;
            cnt += 1;
            graph[sharkY][sharkX] = 0;
            if(cnt == sharkSize) {
                sharkSize += 1;
                cnt = 0;
            }
        }else{
            stop = true;
        }
    }
}

int main(){
    input();
	simulation();
    cout << result;
    return 0;
}
