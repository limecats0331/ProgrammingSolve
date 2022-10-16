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
	var time []int

	sc.Scan()
	input := strings.Split(sc.Text(), " ")

	time = make([]int, len(input))
	for i, t := range input {
		time[i], _ = strconv.Atoi(t)
	}

	time[1] = time[1] - 45
	if time[1] < 0 {
		time[1] = time[1] + 60
		time[0] = time[0] - 1
	}
	if time[0] < 0 {
		time[0] = 23
	}

	fmt.Fprintln(bw, strconv.Itoa(time[0])+" "+strconv.Itoa(time[1]))
	bw.Flush()
}
