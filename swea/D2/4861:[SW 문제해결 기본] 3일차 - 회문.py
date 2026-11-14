# [문제] SWEA 4861 [SW 문제해결 기본] 3일차 - 회문
# [유형] 완전 탐색 / 문자열 (회문)
# [핵심 아이디어]
#   1. NxN 격자의 모든 가로 구간, 세로 구간에서 길이 M 의 부분 문자열이 회문인지 검사한다.
#   2. direction 으로 가로/세로를 구분하고, 양 끝 문자를 비교해 같지 않으면 즉시 중단한다.
#   3. 처음 발견한 회문을 반환한다.
# [시간복잡도] O(N^2 * M)
# [코테 가이드]
#   - 회문 검사는 s == s[::-1] 한 줄로도 된다(Python).
#   - 세로 검사는 격자를 전치(zip(*board))해서 가로 검사 코드를 재사용할 수 있다.
#   - 탐색 범위는 N - M + 1 까지만 돌아야 인덱스 오류가 나지 않는다.

def find_palindrome(board, i, j, M, direction):
    result = []

    for idx in range(M):
        r = i + (0 if direction == 0 else idx)
        c = j + (idx if direction == 0 else 0)

        reverse_r = i + (0 if direction == 0 else M - 1 - idx)
        reverse_c = j + (M - 1 - idx if direction == 0 else 0)

        left = board[r][c]
        right = board[reverse_r][reverse_c]

        if left != right:
            return ""

        result.append(left)

    return ''.join(result)


def solution(board, N, M):
    for direction in range(2):
        row_limit = N if direction == 0 else N - M + 1
        col_limit = N - M + 1 if direction == 0 else N

        for i in range(row_limit):
            for j in range(col_limit):
                result = find_palindrome(board, i, j, M, direction)

                if result:
                    return result

    return ""


T = int(input())

for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    board = [list(input().strip()) for _ in range(N)]

    print(f"#{test_case} {solution(board, N, M)}")