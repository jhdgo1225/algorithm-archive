#include <iostream>
#include <unordered_map>
using namespace std;

unordered_map<string, pair<int, bool>> m;
int N, K, M, F;
bool chk[101][500001];
void input()
{
    cin >> N >> K >> M >> F;
    for (int i=0; i<K; i++)
    {
        for (int j=0; j<M; j++)
        {
            int a; cin >> a;
            chk[i][a] = true;
        }
    }
    for (int i=1; i<=N; i++)
    {
        string str = "";
        for (int j=0; j<K; j++)
            str += (chk[j][i]) ? "Y" : "N";
        if (!m.count(str)) {
            pair<int, bool> tmp = {i, true};
            m[str] = tmp;
        }
        else if (m.count(str) && m[str].second)
            m[str].second = false;
    }
}

void testCase()
{
    while (F--)
    {
        string str; cin >> str;
        cout << ((m.count(str) && m[str].second) ? m[str].first : 0) << '\n';
    }
}

int main()
{
    input();
    testCase();
}