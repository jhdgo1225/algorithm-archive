def solution(board, k):
    return sum([board[i][j] for i in range(0, min(k+1, len(board))) for j in range(0, min(k - i + 1, len(board[0])))])
print(solution([[0, 1, 2],[1, 2, 3],[2, 3, 4],[3, 4, 5]], 2))