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
	total, _ := strconv.Atoi(sc.Text())
	sc.Scan()
	max, _ := strconv.Atoi(sc.Text())

	for i := 0; i < max; i++ {
		sc.Scan()
		input := strings.Split(sc.Text(), " ")
		price, _ := strconv.Atoi(input[0])
		cnt, _ := strconv.Atoi(input[1])
		total = total - (price * cnt)
	}

	var result string
	if total == 0 {
		result = "Yes"
	} else {
		result = "No"
	}

	fmt.Fprintln(bw, result)
	bw.Flush()
}
