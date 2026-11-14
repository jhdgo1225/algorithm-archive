# [문제] SWEA 4839 [SW 문제해결 기본] 2일차 - 이진탐색
# [유형] 이진 탐색 (탐색 횟수 비교)
# [핵심 아이디어]
#   1. 문제에서 정의한 이진 탐색을 그대로 구현해 목표 페이지까지의 탐색 횟수를 센다.
#   2. mid 보다 목표가 크면 s = mid, 작으면 e = mid 로 좁힌다(경계에서 무한 반복을 막기 위해 mid ± 1 보정).
#   3. 탐색 횟수가 적은 사람이 승자이고, 같으면 0 을 출력한다.
# [시간복잡도] O(log P)
# [코테 가이드]
#   - 문제가 표준 이진 탐색(mid ± 1)과 다른 규칙을 주면 그대로 구현해야 횟수가 맞는다.
#   - 이진 탐색은 s <= e 반복, mid = (s + e) // 2 형태를 기본 틀로 암기한다.

def binary_search(s, e, target):
    cnt = 0

    while s <= e:
        mid = (s + e) // 2
        cnt += 1

        if target == mid:
            break
        elif target > mid:
            if s == mid:
                s = mid + 1
            else:
                s = mid
        else:
            if e == mid:
                e = mid - 1
            else:
                e = mid

    return cnt


T = int(input())

for tc in range(1, T + 1):
    p, p1, p2 = map(int, input().split())

    p1_cnt = binary_search(1, p, p1)
    p2_cnt = binary_search(1, p, p2)

    if p1_cnt < p2_cnt:
        result = "A"
    elif p1_cnt > p2_cnt:
        result = "B"
    else:
        result = "0"

    print(f"#{tc} {result}")