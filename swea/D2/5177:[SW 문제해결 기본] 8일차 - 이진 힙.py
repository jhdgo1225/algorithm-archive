# [문제] SWEA 5177 [SW 문제해결 기본] 8일차 - 이진 힙
# [유형] 자료구조 / 최소 힙 구현
# [핵심 아이디어]
#   1. 값을 배열 맨 끝에 넣고 부모와 비교하며 더 작으면 교환한다(상향 이동, sift-up).
#   2. 모든 삽입 후 마지막 노드에서 루트까지 올라가며 조상 노드들의 값 합을 구한다.
#   3. 부모 인덱스는 (cur - 1) // 2 (0-based).
# [시간복잡도] O(N log N)
# [코테 가이드]
#   - 최소/최대 힙은 삽입(sift-up)과 삭제(sift-down)를 직접 구현할 수 있어야 한다.
#   - 단순 정렬 결과만 필요하면 heapq 를 쓰면 되지만, 이 문제처럼 구조가 필요하면 직접 구현한다.
#   - 0-based 부모 (i-1)//2, 자식 2i+1/2i+2 와 1-based 부모 i//2, 자식 2i/2i+1 을 혼동하지 않는다.

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    arr = list(map(int, input().split()))

    heap = []

    for value in arr:
        heap.append(value)
        cur = len(heap) - 1

        while cur > 0:
            parent = (cur - 1) // 2
            if heap[parent] <= heap[cur]:
                break
            heap[parent], heap[cur] = heap[cur], heap[parent]
            cur = parent

    result = 0
    cur = len(heap) - 1

    while cur > 0:
        parent = (cur - 1) // 2
        result += heap[parent]
        cur = parent

    print(f"#{tc} {result}")
