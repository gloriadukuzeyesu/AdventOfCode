
def hashFunction(str):
    currentValue = 0
    for i in range(len(str)):
        currentValue += ord(str[i])
        currentValue = currentValue * 17
        currentValue = currentValue %  256
    return currentValue

with open('Day15/input.txt', 'r') as file:
    data = file.read().split(',')
    data = [ element.strip() for element in data]
    totalHash = 0
    for sequence in data:
       totalHash += hashFunction(sequence)

# print(totalHash)


#part 2

boxes = [[] for _ in range(256)]

# label to the focal lenght
#  label will be key and focal length is valye 

focal_length = {}

for instruction in data:
    if "-" in instruction:
        # remove the last character of instruction
        label = instruction[:-1]
        index = hashFunction(label)
        if label in boxes[index]:
            boxes[index].remove(label)
    else:
        label, length = instruction.split("=")
        length = int(length)

        index = hashFunction(label)
        if label not in boxes[index]:
            boxes[index].append(label)
        
        focal_length[label] = length


# print(focal_length)

total = 0
for box_number, box in enumerate(boxes, 1):
    for lens_slot, label in enumerate(box, 1):
        total += box_number * lens_slot * focal_length[label]

print(total)


    








