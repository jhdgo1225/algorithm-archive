#include <iostream>
#include <map>
using namespace std;

string book[300001];
char boggle[4][4];
bool visited[4][4];
int dy[8] = {-1, 1, 0, 0, -1, -1, 1, 1};
int dx[8] = {0, 0, -1, 1, 1, -1, 1, -1};
int scores[9] = {0, 0, 0, 1, 1, 2, 3, 5, 11};
int w, n;

bool dfs(int order2, int y, int x, int depth, int limit)
{
    if (depth == limit)
        return (true);
    visited[y][x] = true;
    bool flag = false;
    for (int i=0; i<8; i++)
    {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || visited[ny][nx]) continue;
        if (boggle[ny][nx] == book[order2][depth])
            flag = dfs(order2, ny, nx, depth + 1, limit);
        if (flag) {
            visited[y][x] = false;
            return (flag);
        }
    }
    visited[y][x] = false;
    return (flag);
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> w;
    for (int i=0; i<w; i++)
        cin >> book[i];
    cin >> n;
    for (int k=0; k<n; k++)
    {
        for (int i=0; i<4; i++)
            for (int j=0; j<4; j++)
                cin >> boggle[i][j];
        int cnt = 0;
        string str = "";
        int score = 0;
        bool flag;
        for (int idx=0; idx<w; idx++)
        {
            flag = false;
            for (int i=0; i<16; i++)
            {
                if (boggle[i / 4][i % 4] == book[idx][0])
                {
                    flag = dfs(idx, i / 4, i % 4, 1, book[idx].size());
                    if (flag) break;
                }
            }
            if (flag) {
                cnt += 1;
                score += scores[book[idx].size()];
                if (str.size() < book[idx].size() || (str.size() == book[idx].size() && str > book[idx]))
                    str = book[idx];
            }
        }
        cout << score << ' ' << str << ' ' << cnt << '\n';
    }
}