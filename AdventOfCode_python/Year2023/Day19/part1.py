ops = {
    ">": int.__gt__, 
    "<": int.__lt__
}

with open("Day19/input.txt", "r") as file:
    block1, block2 = file.read().split("\n\n")

workflow = {}

for line in block1.splitlines():
    name, res = line[:-1].split("{")
    rules = res.split(",")
    workflow[name] = ([], rules.pop())

    for rule in rules:
        comparison, target = rule.split(":")
        key = comparison[0]
        cmp = comparison[1]
        n = int(comparison[2:])
        workflow[name][0].append((key, cmp, n, target))


# print(workshops)
        
def accept(item, name = "in"):
    if name == "R":
        return False
    if name == "A":
        return True
    
    rules, fallback = workflow[name]

    for key, cmp, n, target in rules:
        if ops[cmp](item[key], n):
            return accept(item, target)
    return accept(item, fallback)

total = 0

for line in block2.splitlines():
    item = {}
    for segmenet in line[1:-1].split(","):
        ch, n = segmenet.split("=")
        item[ch] = int(n)
    # print(item)
    if accept(item):
        total += sum(item.values())
        

print(total)




   


    

