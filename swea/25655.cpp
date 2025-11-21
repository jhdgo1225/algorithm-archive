#include <iostream>
using namespace std;

int main(){
    int T; cin >> T;
    while(T--){
        int N; cin >> N;
		if (N % 2) {
			if (N >= 3) cout << 4;
			else cout << 0;
		}
		for (int i=0; i<N/2; i++)
			cout << 8;
		cout << '\n';
    }
    return 0;
}