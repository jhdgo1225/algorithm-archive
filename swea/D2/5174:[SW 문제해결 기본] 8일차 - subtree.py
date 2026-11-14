# [문제] SWEA 5174 [SW 문제해결 기본] 8일차 - subtree
# [유형] 트리 / 재귀 순회
# [핵심 아이디어]
#   1. 부모-자식 쌍을 읽어 nodes[부모] = [왼쪽 자식, 오른쪽 자식] 으로 저장한다(자식이 없으면 0).
#   2. N 번 노드를 루트로 재귀하며 자기 자신 1 + 왼쪽 서브트리 크기 + 오른쪽 서브트리 크기를 반환한다.
# [시간복잡도] O(E)
# [코테 가이드]
#   - 서브트리 크기는 size(node) = 1 + size(left) + size(right) 의 기본 재귀 패턴이다.
#   - 트리가 깊으면 Python 재귀 제한(기본 1000)에 걸리므로 sys.setrecursionlimit 을 올린다.
#   - 자식 번호가 0 이면 '없음'으로 취급하는 약속을 이용한다.

def traversal(parent, nodes):
    if nodes[parent][0] == 0 and nodes[parent][1] == 0:
        return 1
    cnt = 1
    if nodes[parent][0] > 0:
        cnt += traversal(nodes[parent][0], nodes)
    if nodes[parent][1] > 0:
        cnt += traversal(nodes[parent][1], nodes)
    return cnt


T = int(input())
result = []
for tc in range(1, T + 1):
    E, N = map(int, input().split())
    nodes = [[0, 0] for _ in range(E + 2)]

    arr = list(map(int, input().split()))

    for i in range(0, len(arr), 2):

        parent = arr[i]
        child = arr[i + 1]

        if nodes[parent][0] == 0:
            nodes[parent][0] = child
        else:
            nodes[parent][1] = child

    cnt = traversal(N, nodes)

    result.append(f"#{tc} {cnt}")

print('\n'.join(result))
