//출처: https://sungmin-joo.tistory.com/39 [JooTopia's Blog:티스토리]
#include <iostream>
using namespace std;

int main() {
	int n, i, j, k, res = 0, arr[21][21], arr_road[21][21];
	cin >> n;
	for (i = 1; i <= n; i++) {
		for (j = 1; j <= n; j++) {
			cin >> arr[i][j];
			arr_road[i][j] = arr[i][j];
		}
	}
	for (k = 1; k <= n; k++) {
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				if (i == k || j == k) continue;
				if (arr[i][j] > arr[i][k] + arr[k][j]) {
					cout << -1;
					return 0;
				}
				if (arr[i][j] == arr[i][k] + arr[k][j]) arr_road[i][j] = 0;
			}
		}
	}
	for (i = 1; i <= n; i++)
		for (j = 1; j <= n; j++)
			res += arr_road[i][j];
	//비 방향성 그래프 이고 값이 중복되기때문에 t에 1/2배 해준다.
	cout << res / 2;
	return 0;
}
