import sys

input = sys.stdin.readline

N = int(input())
expr = input().strip()
res = -2147483648  # 2^(-31)

def calculate(a, b, op):
	if op == '+':
		return a + b
	elif op == '-':
		return a - b
	elif op == '*':
		return a * b

def dfs(idx, cur_val):
	global res
	if idx == N - 1:
		res = max(res, cur_val)
		return

	tmp = calculate(cur_val, int(expr[idx + 2]), expr[idx + 1])
	dfs(idx + 2, tmp)

	if idx + 4 < len(expr):
		next_val = calculate(int(expr[idx + 2]), int(expr[idx + 4]), expr[idx + 3])
		tmp = calculate(cur_val, next_val, expr[idx + 1])
		dfs(idx + 4, tmp)

dfs(0, int(expr[0]))
print(res)
