import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] input;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> que;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new int[input[1]][input[0]];
        visited = new boolean[input[1]][input[0]];
        for (int i = 0; i < input[1]; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        que = new ArrayDeque<>();
        //show();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    que.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        bfs();
        //show();
        System.out.println(result() - 1);
    }

    static int result() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input[1]; i++) {
            for (int j = 0; j < input[0]; j++) {
                if (map[i][j] == 0) {
                    return 0;
                }
                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
        }
        return max;
    }

    static void show() {
        for (int[] line : map) {
            System.out.println(Arrays.toString(line));
        }
    }

    static void bfs() {
        while (!que.isEmpty()) {
            int[] visit = que.poll();
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int y = visit[0] + dy[i];
                int x = visit[1] + dx[i];
                if (0 > y || y >= input[1] || 0 > x || x >= input[0]) {
                    continue;
                }
                if (map[y][x] == 0 && !visited[y][x]) {
                    map[y][x] = map[visit[0]][visit[1]] + 1;
                    que.add(new int[]{y, x});
                    visited[y][x] = true;
                }
            }
        }
    }
}
