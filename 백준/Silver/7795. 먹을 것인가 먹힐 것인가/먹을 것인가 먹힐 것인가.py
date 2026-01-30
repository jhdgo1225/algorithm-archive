import sys
from bisect import bisect_left

input = sys.stdin.readline

def main():
	T = int(input())
	for _ in range(T):
		map(int, input().split())
		A = list(map(int, input().split()))
		B = list(map(int, input().split()))
		A.sort()
		B.sort()
		cnt = 0
		for a in A:
			cnt += bisect_left(B, a)
		print(cnt)

if __name__ == '__main__':
	main()