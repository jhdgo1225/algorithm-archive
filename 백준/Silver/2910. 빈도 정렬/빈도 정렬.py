import sys

input = sys.stdin.readline

def main():
	N, C = map(int, input().split())
	number_bags = {}
	idx = 0
	nums = list(map(int, input().split()))
	for num in nums:
		if not num in number_bags:
			number_bags[num] = [idx, 1]
			idx += 1
		else:
			number_bags[num][1] += 1
	li = []
	for k, v in number_bags.items():
		li.append([k, v[0], v[1]])
	li.sort(key=lambda elem: (-elem[2], elem[1]))
	for elem in li:
		for _ in range(elem[2]):
			print(elem[0], end=" ")

if __name__ == '__main__':
	main()