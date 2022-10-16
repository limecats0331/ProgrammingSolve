package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	sc := bufio.NewScanner(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)

	sc.Scan()
	str1 := sc.Text()
	sc.Scan()
	str2 := sc.Text()

	fmt.Fprintln(bw, LCS(str1, str2))
	bw.Flush()
}

func LCS(str1 string, str2 string) int {
	dp := make([][]int, len(str1)+1)
	for i := 0; i < len(str1)+1; i++ {
		dp[i] = make([]int, len(str2)+1)
	}

	for i := 1; i < len(str1)+1; i++ {
		for j := 1; j < len(str2)+1; j++ {
			if str1[i-1] == str2[j-1] {
				dp[i][j] = dp[i-1][j-1] + 1
			} else {
				if dp[i-1][j] > dp[i][j-1] {
					dp[i][j] = dp[i-1][j]
				} else {
					dp[i][j] = dp[i][j-1]
				}
			}
		}
	}

	return dp[len(str1)][len(str2)]
}
