def solution(arr):
    answer = []
    row_length = len(arr)
    col_length = max([len(row) for row in arr])
    for row in arr:
        answer.append(row.copy() + [0] * (row_length - col_length))
    if (row_length < col_length):
        for _ in range(col_length - row_length):
            answer.append([0] * col_length)
    return answer