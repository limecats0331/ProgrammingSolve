import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int H;
    static int W;
    static int[][] map;
    static boolean[][] isVisit;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Set<int[]> deleteList;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        H = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        result = countMap();
        int cnt = 0;
        while (true) {
            deleteList = new HashSet<>();
            isVisit = new boolean[H][W];
            bfs(0, 0);
            for (int[] lo : deleteList) {
                map[lo[0]][lo[1]] = 0;
            }
            int tmp = countMap();
            if (tmp == 0) {
                System.out.println(cnt + 1);
                System.out.println(result);
                return;
            } else {
                result = tmp;
                cnt += 1;
            }
        }
    }

    static int countMap() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 1) cnt += 1;
            }
        }
        return cnt;
    }

    static void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;

                if (map[ny][nx] == 1) {
                    deleteList.add(new int[]{ny, nx});
                }
                if (!isVisit[ny][nx] && map[ny][nx] == 0) {
                    queue.add(new int[]{ny, nx});
                    isVisit[ny][nx] = true;
                }
            }
        }
    }
}
