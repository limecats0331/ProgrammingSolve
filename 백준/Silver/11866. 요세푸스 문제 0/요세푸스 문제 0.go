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

func scanInt() int {
	sc.Scan()
	n, _ := strconv.Atoi(sc.Text())
	return n
}

func main() {
	defer bw.Flush()
	N := scanInt()
	K := scanInt()

	for i := 1; i <= N; i++ {
		que = append(que, i)
	}

	bw.WriteString("<")
	makeNums(K)
	bw.WriteString(">")
}

func makeNums(K int) {
	for len(que) != 0 {
		for i := 0; i < K-1; i++ {
			que = append(que, que[0])
			que = que[1:]
		}

		if len(que) > 1 {
			bw.WriteString(strconv.Itoa(que[0]) + ", ")
		} else {
			bw.WriteString(strconv.Itoa(que[0]))
		}
		que = que[1:]
	}
}
