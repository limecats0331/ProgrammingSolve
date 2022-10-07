import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int H;
    static int W;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        H = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);

        map = new char[H][W];

        int[] jihun = new int[2];
        List<int[]> fires = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().trim().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'J') jihun = new int[]{i, j};
                else if (map[i][j] == 'F') fires.add(new int[]{i, j});
            }
        }

        bfs(jihun, fires);
    }

    // #:wall J:human F:fire
    static void bfs(int[] human, List<int[]> fire) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{human[0], human[1], 0});
        for (int[] f : fire) {
            queue.add(f);
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                //지훈이면
                if (map[now[0]][now[1]] == 'J') {
                    //나갔으면
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                        System.out.println(now[2] + 1);
                        return;
                    }
                    if (map[ny][nx] == '.') {
                        queue.add(new int[]{ny, nx, now[2] + 1});
                        map[ny][nx] = 'J';
                    }
                } else if (map[now[0]][now[1]] == 'F') { // 불이면
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
                    if (map[ny][nx] != '#' && map[ny][nx] != 'F') {
                        queue.add(new int[]{ny, nx, 0});
                        map[ny][nx] = 'F';
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
