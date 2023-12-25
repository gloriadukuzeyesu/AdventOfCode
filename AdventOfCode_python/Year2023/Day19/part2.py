

with open("Day19/input.txt", "r") as file:
    block1, _ = file.read().split("\n\n")

workflows = {}

for line in block1.splitlines():
    name, res = line[:-1].split("{")
    rules = res.split(",")
    workflows[name] = ([], rules.pop())

    for rule in rules:
        comparison, target = rule.split(":")
        key = comparison[0]
        cmp = comparison[1]
        n = int(comparison[2:])
        workflows[name][0].append((key, cmp, n, target))


# print(workshops)


def count(ranges, name = "in"):
    if name == "R":
        return 0
    if name == "A":
        product = 1
        for lo, hi in ranges.values():
            product *= hi - lo + 1
        return product

    rules, fallbacks = workflows[name]

    total = 0

    for key, cmp, n, target in rules:
        lo, hi = ranges[key]
        if cmp == "<":
            T = (lo, n - 1)
            F = (n, hi)
        else:
            T = (n + 1, hi)
            F = (lo, n)
        
        if T[0] <= T[1]:
            copy = dict(ranges)
            copy[key] = T
            total += count(copy, target)
        
        if F[0] <= F[1]:
            ranges = dict(ranges)
            ranges[key] = F
        else:
            break
    else:
        total += count(ranges, fallbacks)

    return total 






print(count({key: (1, 4000) for key in "xmas"}))


        






   


    

