# find the mirror
 
def find_mirror (grid):
    for r in range(1, len(grid)):
        above = grid[:r][::-1]
        below = grid[r:]

        above = above[:len(below)]
        below = below[:len(above)]

        if above == below:
            return r  
    return 0

total = 0

with open("Day13/input.txt", "r") as file:
    data = file.read().split("\n\n")
    print(data)


for block in data:
    grid = block.splitlines()
    row = find_mirror(grid)
    total += row * 100
    col = find_mirror(list(zip(*grid)))
    total += col

  
print(total)