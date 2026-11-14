# [문제] SWEA 4874 [SW 문제해결 기본] 5일차 - Forth
# [유형] 스택 / 후위 표기식 계산
# [핵심 아이디어]
#   1. 숫자는 스택에 push, 연산자를 만나면 두 수를 pop 해 계산한 결과를 다시 push 한다.
#   2. 먼저 pop 한 값이 오른쪽 피연산자(num2), 나중에 pop 한 값이 왼쪽(num1)이다.
#   3. 스택에 피연산자가 2개 미만인데 연산자가 나오거나 끝났을 때 스택 크기가 1 이 아니면 'error'.
# [시간복잡도] O(L)
# [코테 가이드]
#   - 나눗셈은 0 방향으로 버림(truncate)이므로 Python 에서는 // 대신 int(a / b) 를 쓴다(음수 주의).
#   - 후위 표기식의 핵심 오류 조건(피연산자 부족, 결과 개수 != 1)을 외워 둔다.
#   - 마지막 토큰 '.' 은 숫자도 연산자도 아니므로 자연스럽게 무시된다.

T = int(input())

for test_case in range(1, T + 1):
    tokens = input().split()
    st = []
    flag = True

    for token in tokens:
        if token.isdigit():
            st.append(int(token))

        elif token in ['+', '-', '*', '/']:
            if len(st) < 2:
                flag = False
                break

            num2 = st.pop()
            num1 = st.pop()

            if token == '+':
                st.append(num1 + num2)
            elif token == '-':
                st.append(num1 - num2)
            elif token == '*':
                st.append(num1 * num2)
            elif token == '/':
                st.append(int(num1 / num2))

    if flag and len(st) == 1:
        print(f"#{test_case} {st.pop()}")
    else:
        print(f"#{test_case} error")