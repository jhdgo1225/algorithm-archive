#include <iostream>
#include <set>

using namespace std;

int main(int argc, char** argv)
{
	int T;
	freopen("./inputs/24420_input.txt", "r", stdin);
	cin >> T;
	
	for(int test_case = 1; test_case <= T; ++test_case)
	{
		int a, b, tmp;
		set<int> sa, sb;
		
		cin >> a >> b;
		for (int i = 0; i < a; i++) {
			cin >> tmp;
			sa.insert(tmp);
		}
		for (int i = 0; i < b; i++) {
			cin >> tmp;
			sb.insert(tmp);
		}
		
		// 교집합 크기 계산
		int intersection = 0;
		const set<int>& smaller = (sa.size() <= sb.size()) ? sa : sb;
		const set<int>& larger = (sa.size() <= sb.size()) ? sb : sa;
		
		for (const auto& elem : smaller) {
			if (larger.find(elem) != larger.end())
				intersection++;
		}
		
		// 결과 판정
		if (intersection != smaller.size()) {
			cout << '?';  // 교집합이 작은 집합과 같지 않으면 포함 관계 아님
		} else if (sa.size() == sb.size()) {
			cout << '=';  // 교집합이 양쪽 모두와 같으면 동일
		} else {
			cout << (sa.size() < sb.size() ? '<' : '>');  // 포함 관계 방향
		}
		cout << '\n';
	}
	return 0;
}