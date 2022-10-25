import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static final int MAX = 1000000000; //10억
    static int N;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int problemNum = 1;
        while (true) {
            N = Integer.parseInt(br.readLine().trim());
            if (N == 0) break;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            System.out.printf("Problem %d: %d\n", problemNum, dijkstra());
            problemNum++;
        }
    }

    static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.add(new int[]{0, 0, map[0][0]});
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);
        }


        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int y = node[0];
            int x = node[1];
            int cost = node[2];

            if (dist[y][x] < cost) continue;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

                int nextCost = cost + map[ny][nx];
                if (dist[ny][nx] > nextCost) {
                    dist[ny][nx] = nextCost;
                    pq.add(new int[]{ny, nx, nextCost});
                }
            }
        }
        return dist[N - 1][N - 1];
    }
}
