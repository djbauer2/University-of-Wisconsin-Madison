#Dawson Bauer

val = int(input())

contents = []
while val > 0:
    try:
        line = input()
        val -= 1
    except EOFError:
        break
    contents.append(line)
for x in contents:
    print ("Hello, " + x + "!")