#include <string>
#include <cmath>
using ll = long long;
using namespace std;

ll solution(int k, int d) {
    ll answer = 0;
    ll dist = (ll)d * d;
    for (ll i=0; i <= d; i += k) {
        long long rootY = (long long)sqrt(dist - i * i);
        answer += (rootY / k + 1);
    }
    return answer;
}
