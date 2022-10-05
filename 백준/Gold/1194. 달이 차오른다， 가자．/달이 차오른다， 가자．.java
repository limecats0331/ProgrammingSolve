import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Set<Character> keySet = new HashSet<Character>() {{
        add('a');
        add('b');
        add('c');
        add('d');
        add('e');
        add('f');
    }};

    static Set<Character> doorSet = new HashSet<Character>() {{
        add('A');
        add('B');
        add('C');
        add('D');
        add('E');
        add('F');
    }};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        int[] start = new int[2];
        int[] end = new int[2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '1') end = new int[]{i, j};
                if (map[i][j] == '0') start = new int[]{i, j};
            }
        }

        bfs(start, end);
    }

    static void bfs(int[] start, int[] end) {
        //save key value
        boolean[][][] isVisit = new boolean[64][N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        //y,x,cnt,key(binary)
        queue.add(new int[]{start[0], start[1], 0, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int y = now[0];
            int x = now[1];
            int cnt = now[2];
            int key = now[3];
            if (map[y][x] == '1') {
                System.out.println(cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                //범위 밖이면 패스
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                //벽이면 패스
                if (map[ny][nx] == '#') continue;
                //이미 해당 키를 들고 방문했으면
                if (isVisit[key][ny][nx]) continue;

                //그냥들어감
                if (map[ny][nx] == '.' || map[ny][nx] == '0' || map[ny][nx] == '1') {
                    queue.add(new int[]{ny, nx, cnt + 1, key});
                    isVisit[key][ny][nx] = true;
                }
                //키면
                else if (keySet.contains(map[ny][nx])) {
                    int newKey = (key | (1 << Integer.parseInt(String.valueOf(map[ny][nx] - 'a'))));
                    queue.add(new int[]{ny, nx, cnt + 1, newKey});
                    isVisit[newKey][ny][nx] = true;
                }
                //문이면
                else if (doorSet.contains(map[ny][nx])) {
                    int result = (key & (1 << Integer.parseInt(String.valueOf(map[ny][nx] - 'A'))));
                    //열쇠가 있으면
                    if (result != 0) {
                        queue.add(new int[]{ny, nx, cnt + 1, key});
                        isVisit[key][ny][nx] = true;
                    }
                }
            }
        }
        System.out.println("-1");
    }

    static void showMap(int[][] map, String str) {
        System.out.println(str);
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

}
