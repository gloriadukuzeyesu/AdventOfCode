from collections import deque 

with open("Day16/input.txt", 'r') as file:
    grid = file.read().splitlines()
    # print(grid)

def calculate(r,c,dr,dc):
    # r, c, dr, dc
    a = [(r, c, dr, dc)]
    seen = set()
    q = deque(a)

    while q:
        r, c, dr, dc = q.popleft()

        r += dr
        c += dc

        if r < 0 or r >= len(grid) or c < 0 or c >= len(grid[0]):
            continue

        ch = grid[r][c]

        if ch == '.' or (ch == "-" and dc != 0 ) or (ch == "|" and dr != 0):
            if( r, c, dr, dc) not in seen:
                seen.add((r,c,dr,dc))
                q.append((r,c,dr,dc))

        elif ch == "/": 
            dr, dc = -dc, -dr # invert the values 
            if( r, c, dr, dc) not in seen:
                seen.add((r,c,dr,dc))
                q.append((r,c,dr,dc))
        elif ch == "\\":
            dr, dc = dc, dr # no invert the values 
            if( r, c, dr, dc) not in seen:
                seen.add((r,c,dr,dc))
                q.append((r,c,dr,dc))

        else:
            for dr, dc in [(1,0), (-1, 0)] if ch == "|" else [(0,1), (0,-1)]:
                if( r, c, dr, dc) not in seen:
                            seen.add((r,c,dr,dc))
                            q.append((r,c,dr,dc))

    coordinates = {(r, c) for (r, c, _, _) in seen}

    # print(len(coordinates))
    return len(coordinates)


max_val = 0

for r in range(len(grid)):
    max_val = max(max_val, calculate(r, -1, 0, 1))
    max_val = max(max_val, calculate(r, len(grid[0]), 0, -1))

for c in range(len(grid)):
    max_val = max(max_val, calculate(-1, c, 1, 0))
    max_val = max(max_val, calculate(len(grid), c, 1, 0))

print(max_val)

     





