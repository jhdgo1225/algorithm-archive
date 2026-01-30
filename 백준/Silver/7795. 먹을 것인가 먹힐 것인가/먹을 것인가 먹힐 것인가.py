import sys

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
			for b in B:
				if a <= b:
					break
				cnt += 1
		print(cnt)

if __name__ == '__main__':
	main()