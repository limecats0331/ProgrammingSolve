import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, String> cases = new TreeMap<>();
    static int N;
    static int[][] map;
    static Queue<int[]> snake = new ArrayDeque<>();

    //right down left up
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static int nowDir;
    static int time = 0;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());
        int K = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];

        //set initial position
        map[0][0] = 1;
        snake.add(new int[]{0, 0});

        //set apple 2
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
        }

        //input cases
        int L = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            cases.put(time, dir);
        }

        moveSnake();
        System.out.println(time + 1);
    }
    //snake flow


    static void showMap() {
        System.out.printf("-----%d-----\n", time);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }

    static void moveSnake() {
        nowDir = 0;
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, 0});
        while (!que.isEmpty()) {
//            showMap();
            int[] now = que.poll();
            if (cases.containsKey(time)) {
                spin(cases.get(time));
            }

            int y = now[0] + dy[nowDir];
            int x = now[1] + dx[nowDir];

            if (isOverMap(y, x) || map[y][x] == 1) {
                return;
            }

            if (map[y][x] == 0) {
                map[y][x] = 1;
                snake.add(new int[]{y, x});
                int[] removeLocation = snake.poll();
                map[removeLocation[0]][removeLocation[1]] = 0;
                que.add(new int[]{y, x});
            } else if (map[y][x] == 2) {
                map[y][x] = 1;
                snake.add(new int[]{y, x});
                que.add(new int[]{y, x});
            }
            time += 1;
        }
    }

    static void spin(String dir) {
        switch (dir) {
            case "D":
                nowDir = (nowDir + 1) % 4;
                break;
            case "L":
                nowDir = nowDir == 0 ? 3 : nowDir - 1;
                break;
        }
    }

    static boolean isOverMap(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}