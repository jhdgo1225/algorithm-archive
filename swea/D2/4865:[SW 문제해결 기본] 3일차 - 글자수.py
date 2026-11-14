# [문제] SWEA 4865 [SW 문제해결 기본] 3일차 - 글자수
# [유형] 해시 / 빈도수 세기
# [핵심 아이디어]
#   1. str2 의 글자별 개수를 Counter 로 센다.
#   2. str1 의 각 글자에 대해 str2 에서의 개수를 구하고 그중 최댓값을 출력한다.
# [시간복잡도] O(len(str1) + len(str2))
# [코테 가이드]
#   - 빈도수는 Counter(Python), HashMap/배열(Java)을 사용한다.
#   - 없는 키 조회에도 0 이 반환되는 Counter 의 특성을 활용하면 예외 처리가 줄어든다.

from collections import Counter

T = int(input())

for test_case in range(1, T + 1):
    str1 = input()
    str2 = input()

    letters = Counter(str2)
    result = max((letters[ch] for ch in str1), default=0)

    print(f"#{test_case} {result}")