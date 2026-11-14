# [문제] SWEA 5185 [SW 문제해결 구현] 1일차 - 이진수
# [유형] 구현 / 진법 변환 (16진수 -> 2진수)
# [핵심 아이디어]
#   1. 16진수 각 자리를 정수로 바꾸고 상위 비트부터 4비트 (value >> bit) & 1 로 꺼내 이어 붙인다.
#   2. 각 자리는 반드시 4자리 2진수로 표현해야 한다(앞의 0 포함).
# [시간복잡도] O(L)
# [코테 가이드]
#   - Python 은 format(int(ch, 16), '04b') 로 간단히 변환할 수 있다.
#   - 비트 연산 기본: (x >> k) & 1 은 k 번째 비트, x | (1 << k) 는 비트 켜기, x & ~(1 << k) 는 끄기.
#   - 앞자리 0 이 사라지지 않도록 자리수를 고정해서 출력한다.

T = int(input())

result = []

for tc in range(1, T + 1):

    N, hex_str = input().split()

    binary = ''

    for ch in hex_str:
        value = int(ch, 16)

        for bit in range(3, -1, -1):
            binary += str((value >> bit) & 1)

    result.append(f"#{tc} {binary}")

print('\n'.join(result))
