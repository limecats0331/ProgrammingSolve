import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int K;
    static int W;
    static int H;
    static int[][] board;
    static int[] nearY = {-1, 0, 1, 0};
    static int[] nearX = {0, 1, 0, -1};
    static int[] horseY = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] horseX = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int[][][] bfsBoard;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine().trim());

        String[] input = br.readLine().trim().split(" ");
        W = Integer.parseInt(input[0]);
        H = Integer.parseInt(input[1]);

        board = new int[H][W];
        bfsBoard = new int[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            board[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        bfs(K);
    }

    static void bfs(int max) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
//            showAll();
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];
            int cnt = now[2];
            if (y == H - 1 && x == W - 1) {
                System.out.println(bfsBoard[H - 1][W - 1][cnt]);
                return;
            }
            if (cnt < max) {
                for (int i = 0; i < 8; i++) {
                    int hy = y + horseY[i];
                    int hx = x + horseX[i];

                    if (hy < 0 || hy >= H || hx < 0 || hx >= W) continue;
                    if (bfsBoard[hy][hx][cnt + 1] == 0 && board[hy][hx] == 0) {
                        bfsBoard[hy][hx][cnt + 1] = bfsBoard[y][x][cnt] + 1;
//                        System.out.printf("hy : %d, hx : %d, cnt : %d\n", hy, hx, cnt + 1);
                        queue.add(new int[]{hy, hx, cnt + 1});
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int ny = y + nearY[i];
                int nx = x + nearX[i];

                if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
                if (bfsBoard[ny][nx][cnt] == 0 && board[ny][nx] == 0) {
                    bfsBoard[ny][nx][cnt] = bfsBoard[y][x][cnt] + 1;
//                    System.out.printf("ny : %d, nx : %d, cnt : %d\n", ny, nx, cnt);
                    queue.add(new int[]{ny, nx, cnt});
                }
            }
        }
        int result = bfsBoard[H - 1][W - 1][max];
        System.out.println(result == 0 ? -1 : result);
    }

    static void showAll() {
        for (int n = 0; n < K + 1; n++) {
            System.out.printf("each depth : %d\n", n);
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.printf("%d ", bfsBoard[i][j][n]);
                }
                System.out.println();
            }
        }
    }
}
