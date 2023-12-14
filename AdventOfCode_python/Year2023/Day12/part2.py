# use cache for memoization 
#  key will be the pair of cfg and nums and the value will be the count result

cache = {} 
def count ( cfg, nums):
    if cfg == "":
        return 1 if nums == ()  else 0
    if nums == ():
        return 0 if "#" in cfg else 1
    
    result  = 0

    key = (cfg, nums)

    #  see if key has been seen before. 
    if key in cache:
        return cache[key]
    
    if cfg[0] in ".?":
        result += count(cfg[1:], nums)

    if(cfg[0] in "#?"):
        if nums[0] <= len(cfg) and "." not in cfg[:nums[0]] and (nums[0] == len(cfg) or cfg[nums[0]] != "#"):
            result += count (cfg[nums[0] + 1:], nums[1:])
        else:
            result += 0

    #  before returning the result put in the cache
    cache[key] = result

    return result

total = 0



for line in open("Day12/input.txt"):
    cfg, nums = line.split()
    nums = tuple(map(int, nums.split(",")))

    cfg = "?".join([cfg] * 5)
    nums *= 5
    total += count(cfg, nums)

print(total)