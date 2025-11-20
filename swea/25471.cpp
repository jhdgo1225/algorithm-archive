// 정답 참고한 코드 (ID: myunbin)
#include <iostream>
using namespace std;
using ull = unsigned long long;
 
int main(){
    int T; cin >> T;
    while(T--){
        ull N; cin >> N;
        if(N == 1){
            cout << "B\n";
            continue;
        }
        if(N % 2 == 1){
            cout << "B\n";
            continue;
        }
        if( (N & (N - 1)) == 0 ){
            int k = __builtin_ctzll(N);
            if(k % 2 == 1) cout << "B\n";
            else cout << "A\n";
        } else {
            cout << "A\n";
        }
    }
    return 0;
}