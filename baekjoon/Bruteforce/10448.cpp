#include <iostream>
using namespace std;
int dp[1001][4];
int main() {
	dp[0][0] = 1;
	for(int k=1; k<=3; ++k){
		for(int i=1; i<=1000; ++i){
			for(int j=1; j*(j+1)/2<=i; ++j){
				if(dp[i - j*(j+1)/2][k-1])
					dp[i][k]=1;
			}
		}
	}
	int n, k; cin >> n;
    while(n--){
		cin >> k;
        cout << dp[k][3] << endl;
	}
}