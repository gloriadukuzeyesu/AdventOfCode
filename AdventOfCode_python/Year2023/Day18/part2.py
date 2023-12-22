points = [(0,0)]
dirs = {"U": (-1,0), "D": (1,0), "L": (0, -1), "R": (0, 1)}

b = 0
with open("Day18/input.txt", "r") as file:
    for line in file:
        _, _, x = line.split()
        x= x[2:-1]
        n = int(x[:-1], 16)
        b += n
        dr, dc = dirs["RDLU"[int(x[-1])]]
        last_r, last_c = points[-1]
        points.append((last_r + dr * n, last_c + dc * n))

# print(points)
area = 0
for i in range(len(points) - 1):
    area += (points[i][0] * points[i + 1][1] ) - (points[ i + 1][0] * points[i][1])
area += (points[len(points) - 1][0] * points[0][1]) - (points[len(points) - 1][1] * points[0][0]) 

area = abs(area) // 2

# Pick's theorem
i = area - b // 2 + 1

print(i + b)


        
       
      