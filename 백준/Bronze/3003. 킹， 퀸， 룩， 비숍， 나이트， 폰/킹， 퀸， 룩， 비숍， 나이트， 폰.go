package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	cnt := [6]int{1, 1, 2, 2, 2, 8}
	var list [6]int
	var input string

	input, _ = reader.ReadString('\n')
	each := strings.Split(strings.Trim(input, "\r\n"), " ")
	for i, v := range each {
		list[i], _ = strconv.Atoi(v)
	}

	for i := 0; i < 6; i++ {
		fmt.Fprint(writer, strconv.Itoa(cnt[i]-list[i])+" ")
	}
	writer.Flush()
}
