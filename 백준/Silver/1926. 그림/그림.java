import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] isVisit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        isVisit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !isVisit[i][j]) {
                    int cnt = bfs(new int[]{i, j});
                    max = Math.max(max, cnt);
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(max == Integer.MIN_VALUE ? 0 : max);
    }

    static int bfs(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        isVisit[start[0]][start[1]] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }

                if (map[ny][nx] == 1 && !isVisit[ny][nx]) {
                    queue.add(new int[]{ny, nx});
                    isVisit[ny][nx] = true;
                }
            }
        }

        return cnt;
    }
}
