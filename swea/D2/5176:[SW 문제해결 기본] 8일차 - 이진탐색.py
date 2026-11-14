# [문제] SWEA 5176 [SW 문제해결 기본] 8일차 - 이진탐색
# [유형] 트리 / 중위 순회 (완전 이진 탐색 트리)
# [핵심 아이디어]
#   1. 완전 이진 트리를 배열(0-based, 자식 = 2i+1, 2i+2)로 표현한다.
#   2. 이진 탐색 트리의 중위 순회 결과가 오름차순이므로 중위 순회하며 1 부터 N 까지 차례로 채운다.
#   3. 루트(nodes[0])와 N / 2 번째 노드(nodes[N // 2 - 1])의 값을 출력한다.
# [시간복잡도] O(N)
# [코테 가이드]
#   - 이진 탐색 트리 + 중위 순회 = 정렬된 순서. 이 성질로 값을 배치할 수 있다.
#   - 완전 이진 트리의 배열 인덱스: 1-based 는 부모 i, 자식 2i, 2i+1 / 0-based 는 자식 2i+1, 2i+2.
#   - 재귀 깊이는 log N 수준이라 안전하다.

def traversal(idx, nodes):
    if idx >= len(nodes):
        return

    # 왼쪽 자식
    traversal(idx * 2 + 1, nodes)

    # 현재 노드
    global num
    nodes[idx] = num
    num += 1

    # 오른쪽 자식
    traversal(idx * 2 + 2, nodes)


T = int(input())

result = []

for tc in range(1, T + 1):

    N = int(input())
    nodes = [0] * N

    num = 1
    traversal(0, nodes)

    result.append(f"#{tc} {nodes[0]} {nodes[N // 2 - 1]}")

print('\n'.join(result))
