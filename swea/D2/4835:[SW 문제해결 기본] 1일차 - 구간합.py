# [문제] SWEA 4835 [SW 문제해결 기본] 1일차 - 구간합
# [유형] 슬라이딩 윈도우
# [핵심 아이디어]
#   1. 처음 M개의 합을 구한 뒤, 윈도우를 한 칸씩 이동하며 새 원소를 더하고 빠지는 원소를 뺀다.
#   2. 그동안의 최대 합과 최소 합의 차이를 출력한다.
# [시간복잡도] O(N)
# [코테 가이드]
#   - 고정 길이 구간합은 매번 다시 합하지 말고 '더하고 빼기'로 갱신한다.
#   - 윈도우 이동 횟수는 N - M 회임을 헷갈리지 않는다.

T = int(input())

for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    nums = list(map(int, input().split()))

    # 첫 번째 구간의 합
    tmp = sum(nums[:M])
    max_sum = min_sum = tmp

    # 슬라이딩 윈도우
    for i in range(N - M):
        tmp += nums[i + M] - nums[i]

        max_sum = max(max_sum, tmp)
        min_sum = min(min_sum, tmp)

    print(f"#{test_case} {max_sum - min_sum}")