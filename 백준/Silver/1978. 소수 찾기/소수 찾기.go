package main

import (
	"bufio"
	"os"
	"strconv"
)

var (
	sc *bufio.Scanner
	bw *bufio.Writer
)

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	bw = bufio.NewWriter(os.Stdout)
}

func main() {
	defer bw.Flush()
	N := scanInt()

	var result int = 0
	for i := 0; i < N; i++ {
		num := scanInt()
		if isDecimal(num) {
			result++
		}
	}

	bw.WriteString(strconv.Itoa(result))
}

func isDecimal(num int) bool {
	if num == 1 {
		return false
	}
	for i := 2; i < num; i++ {
		if num%i == 0 {
			return false
		}
	}
	return true
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
}
