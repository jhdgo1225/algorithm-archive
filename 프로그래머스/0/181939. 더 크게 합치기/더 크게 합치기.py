def solution(a, b):
	answer: int = 0
	tmp_a: int = a
	tmp_b: int = b
	a_digits: int = 0
	b_digits: int = 0
	while (tmp_a != 0):
		tmp_a //= 10
		a_digits += 1
	while (tmp_b != 0):
		tmp_b //= 10
		b_digits += 1
	a_to_b: int = a * int(10 ** b_digits) + b
	b_to_a: int = b * int(10 ** a_digits) + a
	answer = max(a_to_b, b_to_a)
	return answer