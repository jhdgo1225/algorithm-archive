import sys

input = sys.stdin.readline

def main():
	brackets = input().strip()
	st_brackets = []
	result = 0
	mul = 1
	for idx, bracket in enumerate(brackets):
		if bracket == '(':
			mul *= 2
			st_brackets.append(bracket)
		elif bracket == '[':
			mul *= 3
			st_brackets.append(bracket)
		elif bracket == ']':
			if not len(st_brackets) or st_brackets[-1] != '[':
				result = 0
				break
			if brackets[idx - 1] == '[':
				result += mul
			st_brackets.pop()
			mul //= 3
		elif bracket == ')':
			if not len(st_brackets) or st_brackets[-1] != '(':
				result = 0
				break
			if brackets[idx - 1] == '(':
				result += mul
			st_brackets.pop()
			mul //= 2
	if len(st_brackets):
		print(0)
	else:
		print(result)
if __name__ == "__main__":
	main()
