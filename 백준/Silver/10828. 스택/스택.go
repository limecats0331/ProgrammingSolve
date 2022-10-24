package main

import (
	"bufio"
	"os"
	"strconv"
)

var (
	sc    *bufio.Scanner
	bw    *bufio.Writer
	stack []int
)

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	bw = bufio.NewWriter(os.Stdout)
}

func scanInt() int {
	sc.Scan()
	n, _ := strconv.Atoi(sc.Text())
	return n
}

func main() {
	defer bw.Flush()
	N := scanInt()

	for i := 0; i < N; i++ {
		sc.Scan()
		order := sc.Text()
		switch order {
		case "push":
			num := scanInt()
			stack = append(stack, num)
		case "pop":
			if len(stack) == 0 {
				bw.WriteString("-1\n")
			} else {
				out := stack[len(stack)-1]
				stack = stack[:len(stack)-1]
				bw.WriteString(strconv.Itoa(out) + "\n")
			}
		case "size":
			bw.WriteString(strconv.Itoa(len(stack)) + "\n")
		case "empty":
			if len(stack) == 0 {
				bw.WriteString("1\n")
			} else {
				bw.WriteString("0\n")
			}
		case "top":
			if len(stack) == 0 {
				bw.WriteString("-1\n")
			} else {
				bw.WriteString(strconv.Itoa(stack[len(stack)-1]) + "\n")
			}
		}

	}
}
