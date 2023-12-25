from collections import deque 
import math


class Module:
    def __init__(self, name, type, outputs):
        self.name = name
        self.type = type
        self.outputs = outputs

        if type == "%":
            self.memory = "off"
        else:
            self.memory = {}

    def __repr__(self) -> str:
        return self.name + "{type= " + self.type + ", outputs= " + ",".join(self.outputs) + " , memory= " + str(self.memory) + "}"
    


modules = {}
broadcarst_targets = []

for line in open("Day20/input.txt", "r"):
    left, right = line.strip().split(" -> ")
    outputs = right.split(", ")
    if left == "broadcaster":
        broadcarst_targets = outputs
    else:
        type = left[0]
        name = left[1:]
        modules[name] = Module(name, type, outputs)


for name, module in modules.items():
    for output in module.outputs:
        if output in modules and modules[output].type == "&":
            modules[output].memory[name] = "lo"




(feed,) = [name for name, module in modules.items() if "rx" in module.outputs]

cycle_lengths = {}

seen = {name: 0 for name, module in modules.items() if feed in module.outputs}

print(seen)

presses = 0

while True:
    presses += 1
    q = deque([("brodacaster", x, "lo") for x in broadcarst_targets])

    while q:
        origin, target, pulse = q.popleft()

        if target not in modules:
            continue

        module = modules[target]

        if module.name == feed and pulse == "hi":
            seen[origin] += 1

            if origin not in cycle_lengths:
                cycle_lengths[origin] = presses
            else:
                assert presses == seen[origin] * cycle_lengths[origin]

                if all(seen.values()):
                    x = 1
                    for cycle_length in cycle_lengths.values():
                        x = math.lcm(x, cycle_length)
                    print(x)
                    exit(0)


                        # print(cycle_lengths)

    
        if module.type == "%":
                if pulse == "lo":
                    module.memory = "on" if module.memory == "off" else "off"
                    outgoing = "hi" if module.memory == "on" else "lo"
                    for x in module.outputs:
                        q.append((module.name, x, outgoing))

        else:
            module.memory[origin] = pulse
            outgoing = "lo" if all(x == "hi" for x in module.memory.values()) else "hi"
            for x in module.outputs:
                q.append((module.name, x, outgoing))
      



