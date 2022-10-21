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
	M := scanInt()
	day := make([]int, M)
	page := make([]int, M)
	dp := make([]int, N+1)

	for i := 0; i < M; i++ {
		day[i] = scanInt()
		page[i] = scanInt()
	}

	for i := 0; i < M; i++ {
		for j := N; j >= day[i]; j-- {
			if dp[j-day[i]]+page[i] > dp[j] {
				dp[j] = dp[j-day[i]] + page[i]
			}
		}
	}

	bw.WriteString(strconv.Itoa(dp[N]))
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
}
