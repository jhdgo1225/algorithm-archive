# [문제] SWEA 4866 [SW 문제해결 기본] 4일차 - 괄호검사
# [유형] 스택 / 괄호 검사
# [핵심 아이디어]
#   1. 여는 괄호 '(' '{' 는 스택에 넣는다.
#   2. 닫는 괄호가 나오면 스택이 비어 있지 않고 맨 위가 짝이 맞을 때만 pop 한다.
#   3. 도중에 실패하거나 끝났을 때 스택이 비어 있지 않으면 0, 아니면 1.
# [시간복잡도] O(L)
# [코테 가이드]
#   - 괄호 문제의 3대 실패 조건: 닫는 괄호가 먼저 나옴, 짝이 안 맞음, 끝났는데 스택에 남음.
#   - 괄호 종류가 늘어나면 딕셔너리 {')': '(', '}': '{'} 로 짝을 관리한다.

T = int(input())

for test_case in range(1, T + 1):
    string = input()
    st = []
    result = 1

    for ch in string:
        if ch == '(' or ch == '{':
            st.append(ch)

        elif ch == ')' or ch == '}':
            if not st:
                result = 0
                break

            if (ch == ')' and st[-1] == '(') or \
               (ch == '}' and st[-1] == '{'):
                st.pop()
            else:
                result = 0
                break

    if st:
        result = 0

    print(f"#{test_case} {result}")