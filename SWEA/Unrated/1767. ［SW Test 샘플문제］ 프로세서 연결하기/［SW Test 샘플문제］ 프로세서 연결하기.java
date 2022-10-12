import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int N;
    static List<int[]> core;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int max;
    static int result;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[N][N];
            core = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) core.add(new int[]{i, j});
                }
            }

            max = 0;
            result = Integer.MAX_VALUE;
            dfs(0, 0, map);

            System.out.printf("#%d %d\n", t, result);
        }
    }

    static int getResult(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) cnt += 1;
            }
        }
        return cnt - core.size();
    }

    static void showMap(int[][] map, int depth, int cnt) {
        System.out.printf("show map || depth = %d || cnt = %d\n", depth, cnt);
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    // depth : each core, cnt : connected count
    static void dfs(int depth, int cnt, int[][] map) {
        //find all core
        if (depth == core.size()) {
            if (max < cnt) {
                max = cnt;
                result = getResult(map);
            } else if (max == cnt) {
                result = Math.min(result, getResult(map));
            }
            return;
        }
        //core now
        int[] coreNow = core.get(depth);

        //if in end point
        if (coreNow[0] == 0 || coreNow[0] == N - 1 || coreNow[1] == 0 || coreNow[1] == N - 1) {
            dfs(depth + 1, cnt + 1, map);
        } else { //else
            boolean pick = true;
            for (int i = 0; i < 4; i++) {
                if (isConnect(core.get(depth), i, map)) {
                    dfs(depth + 1, cnt + 1, getLoad(core.get(depth), i, map));
                    pick = false;
                }
            }
            if (pick) {
                dfs(depth + 1, cnt, map);
            }
        }
    }

    static int[][] getLoad(int[] core, int dir, int[][] map) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            tmp[i] = map[i].clone();
        }
        for (int i = 1; i < N; i++) {
            int ny = core[0] + dy[dir] * i;
            int nx = core[1] + dx[dir] * i;

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) break;
            tmp[ny][nx] = 1;
        }
        return tmp;
    }

    static boolean isConnect(int[] core, int dir, int[][] map) {
        for (int i = 1; i < N; i++) {
            int ny = core[0] + dy[dir] * i;
            int nx = core[1] + dx[dir] * i;

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) return true;
            if (map[ny][nx] == 1) return false;
        }
        return false;
    }
}
