import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");

        int C = Integer.parseInt(input[0]);
        int R = Integer.parseInt(input[1]);
        int[][][] map = new int[R][C][2];
        boolean[][] isVisit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            input = br.readLine().trim().split("");
            for (int j = 0; j < C; j++) {
                map[i][j][0] = Integer.parseInt(input[j]);
            }
        }

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        isVisit[0][0] = true;

        bfs(map, isVisit, dy, dx);
    }

    static void bfs(int[][][] map, boolean[][] isVisit, int[] dy, int[] dx) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, 0});
        isVisit[0][0] = true;

        while (!que.isEmpty()) {
            int[] now = que.poll();

            for (int i = 0; i < 4; i++) {
                int y = now[0] + dy[i];
                int x = now[1] + dx[i];

                if (y < 0 || y >= map.length || x < 0 || x >= map[0].length) continue;
                if (isVisit[y][x]) continue;

                isVisit[y][x] = true;
                if (map[y][x][0] == 1) {
                    que.addLast(new int[]{y, x});
                    map[y][x][1] = map[now[0]][now[1]][1] + 1;
                } else {
                    que.addFirst(new int[]{y, x});
                    map[y][x][1] = map[now[0]][now[1]][1];
                }
            }
        }
        System.out.println(map[map.length - 1][map[0].length - 1][1]);
    }

}
