from collections import deque 

with open("Day16/input.txt", 'r') as file:
    grid = file.read().splitlines()
    print(grid)


# r, c, dr, dc
    
a = [(0, -1, 0, 1)]
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

print(len(coordinates))



