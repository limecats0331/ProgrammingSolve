package main

import (
	"bufio"
	"os"
	"strconv"
)

var (
	sc  *bufio.Scanner
	bw  *bufio.Writer
	que []int
)

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	bw = bufio.NewWriter(os.Stdout)
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
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
			que = append(que, num)
		case "pop":
			if len(que) == 0 {
				bw.WriteString("-1\n")
			} else {
				out := que[0]
				que = que[1:]
				bw.WriteString(strconv.Itoa(out) + "\n")
			}
		case "size":
			bw.WriteString(strconv.Itoa(len(que)) + "\n")
		case "empty":
			if len(que) == 0 {
				bw.WriteString("1\n")
			} else {
				bw.WriteString("0\n")
			}
		case "front":
			if len(que) == 0 {
				bw.WriteString("-1\n")
			} else {
				bw.WriteString(strconv.Itoa(que[0]) + "\n")
			}
		case "back":
			if len(que) == 0 {
				bw.WriteString("-1\n")
			} else {
				bw.WriteString(strconv.Itoa(que[len(que)-1]) + "\n")
			}
		}
	}
}
