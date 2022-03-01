x, y, r, n = map(int, input().split())
points = [list(map(int, input().split())) for i in range(n)]

for i in range(n):
	d = (x - points[i][0]) ** 2 + (y - points[i][1]) ** 2 - r ** 2
	if d < 0:
		print("in")
	elif d > 0:
		print("out")
	else:
		print("on")
