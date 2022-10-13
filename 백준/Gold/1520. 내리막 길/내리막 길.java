import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int H;
    static int W;
    static int[][] map;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};
    static int result = 0;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().trim().split(" ");
        H = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        map = new int[H][W];
        dp = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[H - 1][W - 1] = 1;

        for (int i = 0; i < H; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(dp(0, 0));
    }

    static int dp(int y, int x) {
        if (dp[y][x] >= 0) {
//            System.out.println("중복된 좌표 : y = " + y + " x = " + x);
            return dp[y][x];
        }
//        System.out.println("y = " + y + " x = " + x);

        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
            if (map[y][x] > map[ny][nx]) {
                dp[y][x] += dp(ny, nx);
            }
        }
        return dp[y][x];
    }

}
