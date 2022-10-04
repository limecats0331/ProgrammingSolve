import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static int N;
    static int[][] map;
    static int[][] distance;
    static final int MAX = 100000000;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
            }

            dijkstra();
            System.out.printf("#%d %d\n", t, distance[N - 1][N - 1]);
        }
    }

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], MAX);
        }
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int y = now[0];
            int x = now[1];
            int weight = now[2];

            if (distance[y][x] < weight) continue;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

                int cost = weight + map[ny][nx];
                if (cost < distance[ny][nx]) {
                    distance[ny][nx] = cost;
                    pq.add(new int[]{ny, nx, cost});
                }
            }
        }

    }
}
