with open("Day14/input.txt", "r") as file:
    grid = file.read().splitlines()
    grid = list(map("".join, zip(*grid))) # flip / transpose the grid
    grid = ["#".join(["".join(sorted(list(group), reverse= True)) for group in row.split("#")]) for row in grid]
    grid = list(map("".join, zip(*grid)))
    print (sum(row.count("O") * (len(grid) - r ) for r, row in enumerate(grid) ))

    # print()
    # print(grid)




