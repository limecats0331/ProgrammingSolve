import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int N;
    static int W;
    static int H;
    //    static int[][] board;
    static int min;
    static int[] find;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            N = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            H = Integer.parseInt(input[2]);
            int[][] board = new int[H][W];
            find = new int[N];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                input = br.readLine().trim().split(" ");
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(input[j]);
                }
            }

//            showBoard(board);
//            int[] lo = findActivate(board, 2);
//            int[][] newBoard = breakBoard(board, lo, board[lo[0]][lo[1]]);
//            showBoard(board);
//            newBoard = downAll(newBoard);
//            showBoard(board);
//            showBoard(newBoard);

            findAll(board, 0);

            System.out.printf("#%d %d\n", t, min == Integer.MAX_VALUE ? 0 : min);
        }
    }

    static void showBoard(int[][] board) {
        System.out.println("show board!");
        for (int i = 0; i < H; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    //find by all
    static void findAll(int[][] board, int depth) {
        if (depth == N) {
//            System.out.println(count(board));
            min = Math.min(min, count(board));
            return;
        }

        for (int i = 0; i < W; i++) {
            if (board[H - 1][i] == 0) continue;
//            if (depth == 0) showBoard(board);
            int[] lo = findActivate(board, i);
            int[][] nextBoard = breakBoard(board, lo, board[lo[0]][lo[1]]);
            nextBoard = downAll(nextBoard);
            findAll(nextBoard, depth + 1);
        }
        min = Math.min(min, count(board));
    }

    //count
    static int count(int[][] copyBoard) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copyBoard[i][j] != 0) cnt += 1;
            }
        }

        return cnt;
    }

    //down all
    static int[][] downAll(int[][] copyBoard) {
        for (int i = 0; i < W; i++) {
            downLine(copyBoard, i);
        }
        return copyBoard;
    }

    //down line
    static void downLine(int[][] copyBoard, int row) {
        int[] line = new int[H];
        int cnt = 0;
        for (int i = H - 1; i >= 0; i--) {
            if (copyBoard[i][row] != 0) {
                line[cnt] = copyBoard[i][row];
                cnt += 1;
            }
            copyBoard[i][row] = 0;
        }

        for (int i = 0; i < line.length; i++) {
            copyBoard[H - 1 - i][row] = line[i];
        }
    }

    //break ball
    static int[][] breakBoard(int[][] board, int[] location, int size) {
        int[][] copyBoard = new int[H][W];
        for (int i = 0; i < H; i++) {
            copyBoard[i] = board[i].clone();
        }
        List<int[]> chain = new ArrayList<>();
        int y = location[0];
        int x = location[1];
        if (size == 1) {
            copyBoard[y][x] = 0;
            return copyBoard;
        }
        //up
        for (int i = 0; i < size; i++) {
            if (y - i < 0) break;
            if (copyBoard[y - i][x] != 1) chain.add(new int[]{y - i, x, copyBoard[y - i][x]});
            copyBoard[y - i][x] = 0;
        }
        //right
        for (int i = 0; i < size; i++) {
            if (x + i >= W) break;
            if (copyBoard[y][x + i] != 1) chain.add(new int[]{y, x + i, copyBoard[y][x + i]});
            copyBoard[y][x + i] = 0;
        }
        //down
        for (int i = 0; i < size; i++) {
            if (y + i >= H) break;
            if (copyBoard[y + i][x] != 1) chain.add(new int[]{y + i, x, copyBoard[y + i][x]});
            copyBoard[y + i][x] = 0;
        }
        //left
        for (int i = 0; i < size; i++) {
            if (x - i < 0) break;
            if (copyBoard[y][x - i] != 1) chain.add(new int[]{y, x - i, copyBoard[y][x - i]});
            copyBoard[y][x - i] = 0;
        }
        //other size
        for (int[] ints : chain) {
            copyBoard = breakBoard(copyBoard, new int[]{ints[0], ints[1]}, ints[2]);
        }

        return copyBoard;
    }

    //find ball active point
    static int[] findActivate(int[][] board, int location) {
        for (int i = 0; i < H; i++) {
            if (board[i][location] != 0) {
                return new int[]{i, location};
            }
        }
        return new int[]{-1, -1};
    }
}
