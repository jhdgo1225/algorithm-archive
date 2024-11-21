#include <iostream>
using namespace std;

int idx=-1, arr[1000001];
int main()
{
    string str; cin >> str;
    long long res = 0;
    for (int i=0; i<str.size(); i++)
    {
        if (str[i] == '(') {
            if (idx>=0) arr[idx]++;
            idx++;
        } else {
            if (!arr[idx]) {
                cout << idx << ' ' << arr[idx] << '\n';
                res += idx;
            }
            idx--;
            arr[idx + 1] = 0;
        }
    }
    cout << res;
}