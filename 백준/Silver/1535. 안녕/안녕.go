package main

import (
	"bufio"
	"os"
	"strconv"
)

var (
	sc *bufio.Scanner
	bw *bufio.Writer
	dp [101]int
)

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	bw = bufio.NewWriter(os.Stdout)
}

func main() {
	defer bw.Flush()
	N := scanInt()
	L := make([]int, N)
	J := make([]int, N)
	for i := 0; i < N; i++ {
		L[i] = scanInt()
	}
	for i := 0; i < N; i++ {
		J[i] = scanInt()
	}

	for i := 0; i < N; i++ {
		for j := 100; j >= L[i]; j-- {
			if dp[j-L[i]]+J[i] > dp[j] {
				dp[j] = dp[j-L[i]] + J[i]
			}
		}
	}

	bw.WriteString(strconv.Itoa(dp[99]))
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
}
