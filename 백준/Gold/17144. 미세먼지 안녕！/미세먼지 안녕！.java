import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Main {
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static int[][] tmp;
    static int[] air;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        map = new int[R][C];
        air = new int[2];
        int airCnt = 0;
        for (int i = 0; i < R; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    air[airCnt] = i;
                    airCnt += 1;
                }
            }
        }
        for (int i = 0; i < T; i++) {
            spreadDirt();

            cycleUp(air[0]);
            cycleDown(air[1]);
        }
        System.out.println(getResult());
    }

    static void showMap() {
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static int getResult() {
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) result += map[i][j];
            }
        }
        return result;
    }

    // start's y
    static void cycleUp(int start) {
        //move left
        for (int i = start; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        //move up
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        //move right
        for (int i = 0; i < start; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        //move down
        for (int i = C - 1; i > 0; i--) {
            map[start][i] = map[start][i - 1];
        }
        map[start][1] = 0;
        map[start][0] = -1;
    }

    static void cycleDown(int end) {
        //move left
        for (int i = end; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        //move down
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        //move right
        for (int i = R - 1; i > end; i--) {
//            System.out.printf("%d, %d\n", map[i][C - 1], map[i - 1][C - 1]);
            map[i][C - 1] = map[i - 1][C - 1];
        }
        //move up
        for (int i = C - 1; i > 0; i--) {
            map[end][i] = map[end][i - 1];
        }
        map[end][1] = 0;
        map[end][0] = -1;
    }

    static void spreadDirt() {
        tmp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0) {
                    eachDirt(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < R; i++) {
            map[i] = tmp[i].clone();
        }
    }

    static void eachDirt(int[] start) {
        int amount = map[start[0]][start[1]];
        int sep = (int) Math.floor(amount / 5);
        for (int i = 0; i < 4; i++) {
            int y = start[0] + dy[i];
            int x = start[1] + dx[i];
            if (y < 0 || y >= R || x < 0 || x >= C) continue;
            if (map[y][x] == -1) continue;
            tmp[y][x] += sep;
            amount -= sep;
        }
        tmp[start[0]][start[1]] += amount;
    }
}
