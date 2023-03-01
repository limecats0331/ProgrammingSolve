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
            map[i] = Stream.of(br.readLine().trim().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(bfs()+1);
    }

    static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0});
        isVisit[0][0] = true;
        int max = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == N - 1 && now[1] == M - 1) {
                return now[2];
            }

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                int cnt = now[2];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }

                if (map[ny][nx] == 1 && !isVisit[ny][nx]) {
                    queue.add(new int[]{ny, nx, cnt + 1});
                    isVisit[ny][nx] = true;
                }
            }
        }

        return max;
    }
}
