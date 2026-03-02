def solution(arr):
    n = max(len(arr), max(len(row) for row in arr))
    return [r + [0]*(n-len(r)) for r in arr] + [[0]*n]*(n-len(arr))