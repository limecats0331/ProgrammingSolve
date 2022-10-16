package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	sc := bufio.NewScanner(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)

	sc.Scan()
	tc, _ := strconv.Atoi(sc.Text())

	for i := 0; i < tc; i++ {
		sc.Scan()
		input := strings.Split(sc.Text(), "")
		result := calcScore(input)
		fmt.Fprint(bw, strconv.Itoa(result)+"\n")
	}

	bw.Flush()
}

func calcScore(input []string) int {
	var score int = 1
	var result int = 0

	for _, ch := range input {
		if ch == "O" {
			result += score
			score += 1
		} else {
			score = 1
		}
	}
	return result
}
