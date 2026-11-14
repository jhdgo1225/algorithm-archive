# [문제] SWEA 4828 [SW 문제해결 기본] 1일차 - min max
# [유형] 배열 / 최댓값, 최솟값
# [핵심 아이디어]
#   1. 배열을 한 번 순회하며 max 와 min 을 동시에 갱신하고 max - min 을 출력한다.
# [시간복잡도] O(N)
# [코테 가이드]
#   - Python 은 max(nums) - min(nums) 로 한 줄에 풀 수 있다. 초기값은 float('-inf') / float('inf') 를 쓴다.
#   - 초기값을 0 으로 두면 음수 입력에서 틀린다.

T = int(input())

for tc in range(1, T + 1):
    max_num = float('-inf')
    min_num = float('inf')

    N = int(input())
    numbers = list(map(int, input().split()))

    for num in numbers:
        max_num = max(max_num, num)
        min_num = min(min_num, num)

    print(f"#{tc} {max_num - min_num}")