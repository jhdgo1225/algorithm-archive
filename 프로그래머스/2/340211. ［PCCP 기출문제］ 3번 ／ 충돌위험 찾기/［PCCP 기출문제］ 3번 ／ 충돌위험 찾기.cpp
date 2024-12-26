#include <string>
#include <vector>
#include <map>

using namespace std;

map<pair<int, pair<int, int>>, int> logs;

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    int answer = 0;
    for (auto& route: routes)
    {
        int time = 0;
        int cy, cx, ny, nx;
        pair<int, pair<int, int> > key;
        for (int i=0; i<route.size() - 1; i++)
        {
            cy = points[route[i]-1][0];
            cx = points[route[i]-1][1];
            ny = points[route[i+1]-1][0];
            nx = points[route[i+1]-1][1];
            while (cy != ny || cx != nx)
            {
                key = {time++, make_pair(cy, cx)};
                logs[key]++;
                if (cy != ny)
                {
                    if (cy < ny) cy++;
                    else cy--;
                }
                else 
                {
                    if (cx < nx) cx++;
                    else cx--;
                }
            }
        }
        key = {time, make_pair(cy, cx)};
        logs[key]++;
    }
    for (auto& log: logs)
    {
        int cnt = log.second;
        if (cnt > 1) answer++;
    }
    return answer;
}
