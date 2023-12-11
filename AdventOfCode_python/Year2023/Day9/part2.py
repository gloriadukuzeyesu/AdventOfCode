# with open("Day9/input.txt") as file:
#     line = file.read()
#     print(line)


def extrapolate(array):
    if all(x == 0 for x in array):
        return 0
    
    deltas = [ y - x for x, y in  zip(array, array[1:])]
    diff = extrapolate(deltas)
    return array[0] - diff

total = 0

for line in open("Day9/input.txt"):
    nums = list(map(int, line.split()))
    total += extrapolate(nums)


print(total)

# num = zip([1,2,3], [4,5,6], [9,0,10])
# print(tuple(num))




