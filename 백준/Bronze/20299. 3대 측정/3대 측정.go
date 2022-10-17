package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	sc := bufio.NewScanner(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)

	sc.Scan()
	input := strings.Split(sc.Text(), " ")
	N, _ := strconv.Atoi(input[0])
	K, _ := strconv.Atoi(input[1])
	L, _ := strconv.Atoi(input[2])

	var cnt int = 0
	pass := list.New()
	for i := 0; i < N; i++ {
		sc.Scan()
		input := strings.Split(sc.Text(), " ")

		student1, _ := strconv.Atoi(input[0])
		student2, _ := strconv.Atoi(input[1])
		student3, _ := strconv.Atoi(input[2])

		if student1+student2+student3 < K {
			continue
		}
		if student1 < L || student2 < L || student3 < L {
			continue
		}
		cnt += 1
		pass.PushBack(student1)
		pass.PushBack(student2)
		pass.PushBack(student3)
	}

	fmt.Fprintln(bw, cnt)
	for p := pass.Front(); p != nil; p = p.Next() {
		fmt.Fprint(bw, p.Value)
		fmt.Fprint(bw, " ")
	}

	bw.Flush()
}
