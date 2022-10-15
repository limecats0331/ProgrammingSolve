package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	br := bufio.NewScanner(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)
	var dice []int
	var cnt int = 1
	var max int
	var same int

	br.Scan()
	input := strings.Split(br.Text(), " ")
	dice = make([]int, 3)
	for i := 0; i < 3; i++ {
		dice[i], _ = strconv.Atoi(input[i])
	}

	sort.Ints(dice)

	max = dice[0]
	for i := 1; i < 3; i++ {
		if dice[i] == dice[i-1] {
			cnt++
			same = dice[i]
		}
		if max < dice[i] {
			max = dice[i]
		}
	}

	var result int = 0
	if cnt == 3 {
		result = 10000 + same*1000
	} else if cnt == 2 {
		result = 1000 + same*100
	} else {
		result = max * 100
	}

	fmt.Fprint(bw, result)
	bw.Flush()
}
