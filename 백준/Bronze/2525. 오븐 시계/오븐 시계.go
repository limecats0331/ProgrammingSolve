package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	br := bufio.NewScanner(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)

	br.Scan()
	time := strings.Split(br.Text(), " ")
	hour, _ := strconv.Atoi(time[0])
	min, _ := strconv.Atoi(time[1])

	br.Scan()
	next, _ := strconv.Atoi(br.Text())
	min = min + next

	for min >= 60 {
		hour++
		min = min - 60
	}
	if hour >= 24 {
		hour = hour - 24
	}

	fmt.Fprint(bw, strconv.Itoa(hour)+" "+strconv.Itoa(min))
	bw.Flush()
}
