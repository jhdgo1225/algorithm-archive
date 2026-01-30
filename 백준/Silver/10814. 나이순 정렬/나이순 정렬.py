import sys
input = sys.stdin.readline
n=int(input())
members = [input().split() for i in range(n)]
members.sort(key=lambda x: int(x[0]))
for i in members: print(i[0], i[1])