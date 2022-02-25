import os
import random

for idx in range(11):
    a = random.randint(-999999, 999999)
    b = random.randint(-999999, 999999)
    with open("{}.in".format(idx), "w") as f:
        f.write("{}\n{}".format(str(a), str(b)))

    with open("{}.out".format(idx), "w") as f:
        f.write(str(a + b))