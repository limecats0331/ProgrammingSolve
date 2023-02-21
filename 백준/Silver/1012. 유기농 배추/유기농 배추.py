import sys

def delete_bechu(bechu):
    visit = [bechu.pop(0)]
    while visit:
        node = visit.pop(0)
        x = node[0]
        y = node[1]

        if [x-1,y] in bechu:
            visit.append([x-1,y])
            bechu.remove([x-1,y])
        if [x+1,y] in bechu:
            visit.append([x+1,y])
            bechu.remove([x+1,y])
        if [x,y-1] in bechu:
            visit.append([x,y-1])
            bechu.remove([x,y-1])
        if [x,y+1] in bechu:
            visit.append([x,y+1])
            bechu.remove([x,y+1])
    return bechu

def find_worm(bechu):
    count = 0
    while bechu:
        bechu = delete_bechu(bechu)
        count += 1
    return count

test_size = int(sys.stdin.readline())
cases = []
for _ in range(test_size):
    garo, sero, bechu = map(int, sys.stdin.readline().split(" "))
    field = []
    for _ in range(bechu):
        x,y = map(int, sys.stdin.readline().split(" "))
        field.append([x,y])
    cases.append(field)

for case in cases:
    print(find_worm(case))
