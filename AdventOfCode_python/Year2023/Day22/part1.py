
bricks = [ list(map(int, line.replace("~", ",").split(","))) for line in open("Day22/input.txt", "r")]

bricks.sort(key=lambda bricks: bricks[2])

def overlaps(a, b):
    return max(a[0], b[0]) <= min(a[3], b[3]) and max(a[1], b[1]) <= min(a[4], b[4])

for index, brick in enumerate(bricks):
    max_z = 1
    for check in bricks[:index]:
        if overlaps(brick, check):
            max_z = max(max_z, check[5] + 1)
    brick[5] -= brick[2] - max_z
    brick[2] = max_z

bricks.sort(key = lambda bricks:bricks[2])

k_support_v = {i: set() for i in range(len(bricks))}
v_support_k = {i: 0 for i in range(len(bricks))}

for j, upper in enumerate(bricks):
    for i, lower in enumerate(bricks[:j]):
        if overlaps(lower, upper) and upper[2] == lower[5] + 1:
            k_support_v[i].add(j)
            v_support_k[j] += 1


total = 0

for i in range(len(bricks)):
    if all(v_support_k[j] >= 2 for j in k_support_v[i]):
        total += 1

print(total)



