import sys

input = sys.stdin.readline

def main():
	T = int(input())
	li = [input().split() for _ in range(T)]
	li.sort(key=lambda stu: (-int(stu[1]), int(stu[2]), -int(stu[3]), stu[0]))
	for stu in li:
		print(stu[0])

if __name__ == '__main__':
	main()