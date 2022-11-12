import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    static int N;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int[][] debug;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());

            map = new int[N][N];
            debug = new int[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = map[i][j] == 5 ? 0 : map[i][j];
                }
            }

            String[] input = br.readLine().trim().split(" ");
            int[] start = new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])};
            input = br.readLine().trim().split(" ");
            int[] end = new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])};

            int cnt = BFS(start, end);
            System.out.printf("#%d %d\n", t, cnt == 0 ? -1 : cnt);
        }
    }

    static int BFS(int[] start, int[] end) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{start[0], start[1], 0});

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int cnt = now[2];

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (map[ny][nx] == 1) continue;
                if (debug[ny][nx] != 0 && debug[ny][nx] <= cnt) continue;

                if (map[ny][nx] == 0) { //그냥 들어갈 수 있음
                    que.add(new int[]{ny, nx, cnt + 1});
                    debug[ny][nx] = cnt + 1;
                } else if (map[ny][nx] == 2) { //소용돌이라서 기다려야됨
                    int newTime = returnWaitTime(cnt);
                    que.add(new int[]{ny, nx, newTime});
                    debug[ny][nx] = newTime;
                }
            }

//            showNewMap(debug);
        }
        return debug[end[0]][end[1]];
    }

    static void showNewMap(int[][] map) {
        System.out.println("========= newMap ===========");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
    }

    static int returnWaitTime(int time) {
        for (int i = 1; i <= 3; i++) {
            if ((time + i) % 3 == 0) {
                return time + i;
            }
        }
        return -1;
    }
}
