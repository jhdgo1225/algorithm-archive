#include<iostream>

using namespace std;

int gcd(int a, int b) {
    while (b) {
        a %= b;
        std::swap(a, b);
    }
    return a;
}

long long lcm(int a, int b) {
    if (a == 0 || b == 0) return 0;
    return ((long long)a * b) / gcd(a, b);
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	freopen("24696_input.txt", "r", stdin);
	cin>>T;
	/*
	   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/
	for(test_case = 1; test_case <= T; ++test_case)
	{
        int a, b,c;
        cin >> a >> b >> c;
        cout << ((lcm(lcm(a, b), c) % 2) ? 2 : 1) << '\n';

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}