str = input()
result = ''.join([c.upper() if c.islower() else c.lower() for c in str])
print(result)