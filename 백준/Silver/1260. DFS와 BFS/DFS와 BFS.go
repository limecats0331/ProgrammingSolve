package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

var N int
var M int
var V int
var isVisit []bool

func main() {
	br := bufio.NewReader(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)

	fmt.Fscanln(br, &N, &M, &V)

	arr := make([][]int, N+1)
	for i := 0; i < N+1; i++ {
		arr[i] = make([]int, N+1)
	}

	for i := 0; i < M; i++ {
		var node1 int
		var node2 int
		fmt.Fscanln(br, &node1, &node2)
		arr[node1][node2] = 1
		arr[node2][node1] = 1
	}
	isVisit = make([]bool, N+1)
	dfs(V, arr)

	fmt.Println()
	bfs(V, arr)

	// fmt.Fprintln(bw, arr)

	bw.Flush()
}

func dfs(now int, arr [][]int) {
	if isVisit[now] {
		return
	}
	fmt.Print(strconv.Itoa(now) + " ")
	isVisit[now] = true
	for i := 1; i < N+1; i++ {
		if arr[now][i] == 1 && !isVisit[i] {
			dfs(i, arr)
		}
	}
}

func bfs(start int, arr [][]int) {
	visit := make([]bool, N+1)
	que := []int{start}
	visit[start] = true

	for len(que) != 0 {
		now := que[0]
		que = que[1:]
		fmt.Print(strconv.Itoa(now) + " ")

		for i := 1; i < N+1; i++ {
			if arr[now][i] == 1 && !visit[i] {
				que = append(que, i)
				visit[i] = true
			}
		}
	}
}
