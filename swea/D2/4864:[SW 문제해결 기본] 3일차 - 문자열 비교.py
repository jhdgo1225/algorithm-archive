# [문제] SWEA 4864 [SW 문제해결 기본] 3일차 - 문자열 비교
# [유형] 문자열 검색
# [핵심 아이디어]
#   1. 찾을 문자열이 대상 문자열 안에 포함되어 있으면 1, 아니면 0 을 출력한다.
#   2. Python 의 'in' 연산자로 부분 문자열 포함 여부를 확인한다.
# [시간복잡도] O(N * M) (최악, 실제로는 매우 빠름)
# [코테 가이드]
#   - 직접 구현하면 이중 반복문, 최적화가 필요하면 KMP / 해시(라빈-카프)를 쓴다.
#   - 코테에서는 라이브러리(in, find, indexOf)를 먼저 고려한다.

T = int(input())

for test_case in range(1, T + 1):
    needle = input()
    haystack = input()

    result = 1 if needle in haystack else 0

    print(f"#{test_case} {result}")