package main

import (
	"bufio"
	"math"
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

func scanInt() int {
	sc.Scan()
	n, _ := strconv.Atoi(sc.Text())
	return n
}

func main() {
	defer bw.Flush()
	M := scanInt()
	N := scanInt()
	for i := M; i <= N; i++ {
		if isPrime(i) {
			bw.WriteString(strconv.Itoa(i) + "\n")
		}
	}
}

func isPrime(num int) bool {
	if num == 1 {
		return false
	}
	limit := int(math.Sqrt(float64(num)))
	for i := 2; i <= limit; i++ {
		if num%i == 0 {
			return false
		}
	}
	return true
}
