import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    static int N;
    static int K;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int result;
    static int[][] debug;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);
            map = new int[N][N];

            int max = 0;
            for (int i = 0; i < N; i++) {
                int[] line = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line[j];
                    if (max < map[i][j]) max = map[i][j];
                }
            }

            Queue<int[]> start = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) start.add(new int[]{i, j, max, 0, 1});
                }
            }

            result = 0;
            for (int[] peaks : start) {
                boolean[][] isVisit = new boolean[N][N];
                isVisit[peaks[0]][peaks[1]] = true;
                debug = new int[N][N];
                debug[peaks[0]][peaks[1]] = max;
                dfs(peaks[0], peaks[1], isVisit, max, 1, false);
            }
            System.out.printf("#%d %d\n", t, result);
        }
    }

    static void dfs(int y, int x, boolean[][] isVisit, int height, int cnt, boolean broken) {
        result = Math.max(result, cnt);
        debug[y][x] = height;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (!broken) {
                if (map[ny][nx] - K < height && !isVisit[ny][nx]) {
                    isVisit[ny][nx] = true;
                    if (map[ny][nx] >= height) {
                        dfs(ny, nx, isVisit, height - 1, cnt + 1, true);
                    } else {
                        dfs(ny, nx, isVisit, map[ny][nx] - 1, cnt, true);
                    }
                    isVisit[ny][nx] = false;
                }
            }
            if (map[ny][nx] < height && !isVisit[ny][nx]) {
                isVisit[ny][nx] = true;
                dfs(ny, nx, isVisit, map[ny][nx], cnt + 1, broken);
                isVisit[ny][nx] = false;
            }
        }
    }
}
