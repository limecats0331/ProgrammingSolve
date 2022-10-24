package main

import (
	"bufio"
	"os"
	"strconv"
)

var (
	sc   *bufio.Scanner
	bw   *bufio.Writer
	fact [11]int
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
	fact[0] = 1
	fact[1] = 1
	for i := 2; i < 11; i++ {
		fact[i] = i * fact[i-1]
	}

	N := scanInt()
	K := scanInt()

	result := fact[N] / (fact[N-K] * fact[K])
	bw.WriteString(strconv.Itoa(result))
}
