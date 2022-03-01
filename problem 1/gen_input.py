import random

for i in range(10):
	fp = open(str(i) + ".in", "w")
	x = random.randint(-128, 128)
	y = random.randint(-128, 128)
	r = random.randint(1, 256)
	n = random.randint(1, 256)
	line = str(x) + " " + str(y) + " " + str(r) + " " + str(n) + "\n"
	fp.write(line)
	for j in range(n):
		a = random.randint(-128, 128)
		b = random.randint(-128, 128)
		line = str(a) + " " + str(b) + "\n"
		fp.write(line)
	fp.close
