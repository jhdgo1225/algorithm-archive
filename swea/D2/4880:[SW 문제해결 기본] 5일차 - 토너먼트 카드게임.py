# [문제] SWEA 4880 [SW 문제해결 기본] 5일차 - 토너먼트 카드게임
# [유형] 분할 정복 / 재귀 (토너먼트)
# [핵심 아이디어]
#   1. 구간 [s, e] 를 반으로 나눠 각 구간의 승자를 재귀로 구한 뒤 두 승자를 대결시킨다.
#   2. 가위바위보 규칙: 1 이 2 에게, 2 가 3 에게, 3 이 1 에게 진다. 비기면 번호가 작은(앞) 사람이 이긴다.
#   3. 구간 길이가 1 이면 그 번호를 반환한다.
# [시간복잡도] O(N)
# [코테 가이드]
#   - 토너먼트/구간 문제는 mid = (s + e) // 2 로 나누는 분할 정복이 자연스럽다.
#   - 비겼을 때 누가 이기는지(번호가 작은 쪽) 조건을 반드시 확인한다.
#   - 출력은 0-based 인덱스에 +1 해야 한다.

def rsp(p1_num, p2_num, cards):
    if cards[p1_num] == cards[p2_num]:
        return p1_num

    if (cards[p1_num] == 1 and cards[p2_num] == 2
        or cards[p1_num] == 2 and cards[p2_num] == 3
        or cards[p1_num] == 3 and cards[p2_num] == 1):
        return p2_num

    return p1_num


def play(s, e, cards):
    if e - s == 0:
        return s

    p1_num = play(s, (s + e) // 2, cards)
    p2_num = play((s + e) // 2 + 1, e, cards)

    return rsp(p1_num, p2_num, cards)


T = int(input())

for tc in range(1, T + 1):

    N = int(input())

    cards = list(map(int, input().split()))

    print(f"#{tc} {play(0, N - 1, cards) + 1}")
