from collections import deque

# use a queue for bfs

grid = open("Day10/input.txt").read().strip().splitlines()

for r, row in enumerate(grid):
    for c, char in enumerate(row):
        if char == "S":
            sr = r
            sc = c
            break
    else:
        continue
    break

seen = {(sr,sc)}

q = deque([(sr, sc)])

all_possible_S_values = {"|", "-", "J", "L", "7", "F"}


while q:
    r,c = q.popleft()
    ch = grid[r][c]

    if r > 0 and ch in "S|JL" and grid[r - 1][c] in "|7F" and (r -1, c) not in seen:
        seen.add((r - 1, c))
        q.append((r - 1, c))
        if ch == "S":
            all_possible_S_values  &= {"|", "J", "L"}

    if r < len(grid) - 1 and ch in "S|7F" and grid[r + 1][c] in "|JL" and (r + 1, c) not in seen:
        seen.add((r + 1, c))
        q.append((r + 1, c))
        if ch == "S":
            all_possible_S_values  &= {"|", "7", "F"}

    # move left 

    if c > 0 and ch in "S-J7" and grid[r][c - 1] in "-LF" and (r, c - 1) not in seen:
        seen.add((r, c - 1))
        q.append((r, c - 1))
        if ch == "S":
            all_possible_S_values  &= {"-", "J", "7"}



    # move right
    if c < len(grid[r]) - 1 and ch in "S-LF" and grid[r][c + 1] in "-J7" and (r, c + 1) not in seen:
        seen.add((r, c + 1))
        q.append((r, c + 1))
        if ch == "S":
            all_possible_S_values  &= {"-", "L", "F"}


assert len(all_possible_S_values) == 1
(S,) = all_possible_S_values
# print(S)

grid = [row.replace("S", S) for row in grid]
grid = ["".join(ch if (r,c) in seen else "."for c, ch in enumerate(row)) for r, row in enumerate(grid)]


# print("\n".join(grid))


outside = set()

for r, row in enumerate(grid):
    within = False
    up = None 
    for c, ch in enumerate(row):
        if ch == "|":
            assert up is None
            within = not within
        elif ch == "-":
            assert up is not None
        elif ch in "LF":
            assert up is None 
            up = ch == "L"
        elif ch in "7J":
            assert up is not None
            if ch != ("J" if up else "7"):
                within = not within
            up = None
        elif ch == ".":
            pass
        else:
            raise RuntimeError(f"Unexpected character (horizontal): {ch}")
        if not within:
            outside.add((r,c))


print(len(grid) * len(grid[0])   - len (outside | seen))






            























