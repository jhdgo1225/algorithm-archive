# [문제] SWEA 4873 [SW 문제해결 기본] 4일차 - 반복문자 지우기
# [유형] 스택
# [핵심 아이디어]
#   1. 문자열을 앞에서부터 보며 스택의 top 과 같은 문자가 오면 pop(연속된 중복 제거)한다.
#   2. 다르면 push 한다. 최종 스택 크기가 남은 글자 수다.
# [시간복잡도] O(L)
# [코테 가이드]
#   - '바로 앞과 같으면 제거'는 스택의 대표 유형이다(인접 중복 제거, 괄호 짝 등).
#   - 스택 대신 문자열을 매번 잘라 붙이면 O(L^2) 이 되어 시간 초과가 날 수 있다.

T = int(input())

for test_case in range(1, T + 1):
    string = input()
    st = []

    for ch in string:
        if st and st[-1] == ch:
            st.pop()
            continue

        st.append(ch)

    print(f"#{test_case} {len(st)}")