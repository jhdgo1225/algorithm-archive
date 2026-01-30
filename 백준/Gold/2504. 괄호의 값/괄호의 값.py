import sys

input = sys.stdin.readline

def main():
	brackets = input().strip()
	st_brackets = []
	st_numbers = []
	flag = 0
	for bracket in brackets:
		if bracket == '(' or bracket == '[':
			tmp = 1
			st_brackets.append(bracket)
		else:
			tmp2 = 0
			while (len(st_brackets) != 0 and st_brackets[-1] == '.'):
				tmp2 += st_numbers[-1]
				st_brackets.pop()
				st_numbers.pop()
			if len(st_brackets) == 0:
				flag = 1
				break
			if tmp2 > 0:
				tmp = tmp2
			if bracket == ')' and st_brackets[-1] == '(':
				tmp *= 2
			elif bracket == ']' and st_brackets[-1] == '[':
				tmp *= 3
			else:
				flag = 1
				break
			st_numbers.append(tmp)
			st_brackets.pop()
			st_brackets.append('.')
	res = 0
	while (len(st_brackets) and st_brackets[-1] == '.'):
		res += st_numbers[-1]
		st_brackets.pop()
		st_numbers.pop()
	if (flag or len(st_brackets)):
		print(0)
	else:
		print(res)
if __name__ == "__main__":
	main()