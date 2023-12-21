
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

print(totalHash)




