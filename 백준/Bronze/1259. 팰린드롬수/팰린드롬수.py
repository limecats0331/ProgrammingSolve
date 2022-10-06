input_num = ''

while (input_num != '0'):
    input_num = input()
    if input_num == '0':
        break
    tmp = input_num[::-1]
    if tmp == input_num:
        print('yes')
    else:
        print('no')
