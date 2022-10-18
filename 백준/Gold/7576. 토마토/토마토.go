package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var H int
var W int
var arr [][]int64
var dy = []int{-1, 0, 1, 0}
var dx = []int{0, 1, 0, -1}

func main() {
	br := bufio.NewReader(os.Stdin)
	bw := bufio.NewWriter(os.Stdout)

	fmt.Fscanln(br, &W, &H)
	arr = make([][]int64, H)
	for i := 0; i < H; i++ {
		arr[i] = make([]int64, W)
	}

	que := make([][]int, 0)

	for i := 0; i < H; i++ {
		input, _, _ := br.ReadLine()
		line := strings.Split(strings.Trim(string(input), "\r\n"), " ")
		for j := 0; j < W; j++ {
			num, _ := strconv.ParseInt(line[j], 0, 64)
			arr[i][j] = num
			if arr[i][j] == 1 {
				que = append(que, []int{i, j, 0})
			}
		}
	}

	result := bfs(que)
	if !allClear() {
		result = -1
	}
	// fmt.Fprintln(bw, result)
	bw.WriteString(strconv.Itoa(result))
	bw.Flush()
}

func allClear() bool {
	for i := 0; i < H; i++ {
		for j := 0; j < W; j++ {
			if arr[i][j] == 0 {
				return false
			}
		}
	}
	return true
}

func bfs(que [][]int) int {
	// que := [][]int{start}
	var max int = 0
	for len(que) != 0 {
		now := que[0]
		que = que[1:]

		max = now[2]

		for i := 0; i < 4; i++ {
			ny := now[0] + dy[i]
			nx := now[1] + dx[i]

			if ny < 0 || ny >= H || nx < 0 || nx >= W {
				continue
			}
			if arr[ny][nx] == -1 {
				continue
			}
			if arr[ny][nx] == 0 {
				que = append(que, []int{ny, nx, now[2] + 1})
				arr[ny][nx] = 1
			}
		}
	}
	return max
}
