package main

import (
	"bufio"
	"os"
	"sort"
	"strconv"
)

var (
	sc      *bufio.Scanner
	bw      *bufio.Writer
	arr     [101][101]int
	isVisit [101][101]bool
	W       int
	H       int
	K       int
	dy      [4]int
	dx      [4]int
	size    []int
)

type location struct {
	y int
	x int
}

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	bw = bufio.NewWriter(os.Stdout)
	dy = [4]int{-1, 0, 1, 0}
	dx = [4]int{0, 1, 0, -1}
}

func main() {
	defer bw.Flush()
	H = scanInt()
	W = scanInt()
	K = scanInt()

	for i := 0; i < K; i++ {
		fx := scanInt()
		fy := scanInt()
		tx := scanInt()
		ty := scanInt()
		color(fx, fy, tx, ty)
	}

	var cnt int = 0
	for i := 0; i < H; i++ {
		for j := 0; j < W; j++ {
			if !isVisit[i][j] && arr[i][j] == 0 {
				isVisit[i][j] = true
				size = append(size, BFS(location{i, j}))
				cnt++
			}
		}
	}

	sort.Slice(size, func(i, j int) bool {
		return size[i] < size[j]
	})

	bw.WriteString(strconv.Itoa(cnt) + "\n")
	for i := 0; i < len(size); i++ {
		bw.WriteString(strconv.Itoa(size[i]) + " ")
	}
}

func BFS(start location) int {
	que := make([]location, 0)
	que = append(que, start)
	var cnt int = 0

	for len(que) != 0 {
		now := que[0]
		que = que[1:]
		cnt++

		for i := 0; i < 4; i++ {
			ny := now.y + dy[i]
			nx := now.x + dx[i]

			if ny < H && ny >= 0 && nx < W && nx >= 0 {
				if !isVisit[ny][nx] && arr[ny][nx] == 0 {
					que = append(que, location{ny, nx})
					isVisit[ny][nx] = true
				}
			}
		}
	}
	return cnt
}

func color(fx, fy, tx, ty int) {
	for i := fy; i < ty; i++ {
		for j := fx; j < tx; j++ {
			arr[i][j] = 1
		}
	}
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
}
