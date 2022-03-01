x1, y1, r1 = input().split()
x1 = int(x1)
y1 = int(y1)
r1 = int(r1)

x2, y2, r2 = input().split()
x2 = int(x2)
y2 = int(y2)
r2 = int(r2)

num = int(input())
in1 = 0
in2 = 0
inBoth = 0
inNo = 0

for i in range(num):
	x, y = input().split()
	x = int(x)
	y = int(y)
	if ((x-x1)**2 + (y-y1)**2 <= r1**2 and (x-x2)**2+(y-y2)**2 <= r2**2):
		inBoth = inBoth + 1
	elif ((x-x1)**2 + (y-y1)**2):
		in1 = in1 + 1
	elif ((x-x2)**2 + (y-y2)**2):
		in2 = in2 + 1

inNo = num - in1 - in2 - inBoth

print(inBoth)
print(in1)
print(in2)
print(inNo)


# print(x1)
# print(type(x1))
