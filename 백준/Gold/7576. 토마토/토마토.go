package main

import (
	"bufio"
	"os"
	"strconv"
)

var (
	sc     *bufio.Scanner
	wr     *bufio.Writer
	N, M   int
	arr    [1000][1000]int
	cnt    int
	dx, dy [4]int
	q      []status
)

type status struct {
	index
	day int
}

type index struct {
	x, y int
}

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	wr = bufio.NewWriter(os.Stdout)
	dx = [4]int{0, 0, 1, -1}
	dy = [4]int{1, -1, 0, 0}
}

func main() {
	defer wr.Flush()
	M = scanInt()
	N = scanInt()
	q = make([]status, 0, M*N)
	for i := 0; i < N; i++ {
		for j := 0; j < M; j++ {
			arr[i][j] = scanInt()
			if arr[i][j] == 1 {
				q = append(q, status{index{i, j}, 0})
			}
		}
	}
	bfs()
	if check() {
		wr.WriteString(strconv.Itoa(cnt))
	} else {
		wr.WriteString("-1")
	}
}

func bfs() {
	for len(q) != 0 {
		in := q[0]
		q = q[1:]
		x := in.x
		y := in.y
		if in.day > cnt {
			cnt = in.day
		}
		for i := 0; i < 4; i++ {
			newX := x + dx[i]
			newY := y + dy[i]
			if newX >= 0 && newX < N && newY >= 0 && newY < M && arr[newX][newY] == 0 {
				arr[newX][newY] = 1
				q = append(q, status{index{newX, newY}, in.day + 1})
			}
		}
	}
}

func check() bool {
	for i := 0; i < N; i++ {
		for j := 0; j < M; j++ {
			if arr[i][j] == 0 {
				return false
			}
		}
	}
	return true
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
}