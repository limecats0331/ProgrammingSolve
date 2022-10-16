import sys

size = int(sys.stdin.readline())
answer = []

for i in range(size):
    result = 0
    answer.append( (str(sys.stdin.readline() ) ) )

    score_split = answer[i].strip().split("X")
    score_split = list(filter(None, score_split))
    
    for j in range(len(score_split)):
        num = len(score_split[j])
        result += num*(num+1)/2
    print(int(result))