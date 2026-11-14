# [문제] SWEA 4834 [SW 문제해결 기본] 1일차 - 숫자 카드
# [유형] 카운팅 배열
# [핵심 아이디어]
#   1. 카드의 숫자가 0~9 이므로 길이 10 배열에 등장 횟수를 센다.
#   2. (횟수, 숫자) 를 기준으로 최대값을 고르면 횟수가 같을 때 더 큰 숫자가 선택된다.
# [시간복잡도] O(N)
# [코테 가이드]
#   - 동점 처리는 max(..., key=lambda x: (빈도, x)) 처럼 튜플 키로 간단히 해결한다.
#   - 공백 없이 붙어서 입력되는 숫자열은 input() 한 줄을 문자 단위로 순회한다.

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    s = input()

    nums = [0] * 10

    for ch in s:
        nums[int(ch)] += 1

    result = max(range(10), key=lambda x: (nums[x], x))

    print(f"#{tc} {result} {nums[result]}")