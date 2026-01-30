import sys

input = sys.stdin.readline

def main():
	input_flag = False
	count_flag = False
	idx = 0
	N = 0
	nums = []
	while True:
		inputs = input().split()
		if not input_flag:
			N = int(inputs[0])
			inputs = inputs[1:]
			input_flag = True
		for elem in inputs:
			nums.append(int(elem[::-1]))
			idx += 1
			if idx == N:
				count_flag = True
				break
		if count_flag:
			break
	nums.sort()
	for num in nums:
		print(num)

if __name__ == '__main__':
	main()