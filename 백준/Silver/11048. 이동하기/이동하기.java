import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int[][] dp;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }

        int result = getResult(0, 0);
        System.out.println(result);
    }

    static int getResult(int y, int x) {
        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = map[y][x];
        int add = 0;
        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            add = Math.max(add, getResult(ny,nx));
        }
        dp[y][x] += add;
        return dp[y][x];
    }

}
