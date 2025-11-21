import sys

sys.stdin = open("./inputs/17937_input.txt", "r")

T = int(input())
for i in range(T):
	a, b = map(int, input().split())
	print(f"#{i + 1} {a if (a == b) else 1}")
	T -= 1
