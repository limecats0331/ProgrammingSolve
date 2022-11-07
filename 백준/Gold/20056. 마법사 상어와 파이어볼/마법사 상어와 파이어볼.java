import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    static int N;
    static int M;
    static int K;

    static List<FireBall>[][] map;
    static List<FireBall>[][] newMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().trim().split(" ");
            int y = Integer.parseInt(input[0]) - 1;
            int x = Integer.parseInt(input[1]) - 1;
            int m = Integer.parseInt(input[2]);
            int s = Integer.parseInt(input[3]);
            int d = Integer.parseInt(input[4]);

            map[y][x].add(new FireBall(y, x, m, s, d));

        }

        //이동
        for (int t = 0; t < K; t++) {
            makeNewMap();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].size() > 0) {
                        for (FireBall fireBall : map[i][j]) {
                            fireBall.moveNext();
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newMap[i][j].size() > 1) {
                        separateBall(i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = newMap[i][j];
                }
            }
        }

        System.out.println(getResult());

    }

    static void makeNewMap() {
        newMap = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }
    }

    static void showMap() {
        System.out.println("========showmap===========");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() > 0) {
                    System.out.printf("%d\t", map[i][j].get(0).m);
                } else {
                    System.out.printf("_\t");
                }
            }
            System.out.println();
        }
    }

    static void showNewMap() {
        System.out.println("----------show newmap-----------");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newMap[i][j].size() > 0) {
                    System.out.printf("%d\t", newMap[i][j].get(0).m);
                } else {
                    System.out.printf("_\t");
                }
            }
            System.out.println();
        }
    }

    static int getResult() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() > 0) {
                    for (FireBall fireBall : map[i][j]) {
                        result += fireBall.m;
                    }
                }
            }
        }
        return result;
    }

    static void separateBall(int y, int x) {
        int[] dirArr = defSeparateDIr(newMap[y][x]);
        int newSpeed = getSpeed(newMap[y][x]);
        int newWeight = getWeight(newMap[y][x]);

        newMap[y][x] = new ArrayList<>();

        if (newWeight == 0) { //무게가 0이 되면 사라진다.
            return;
        }

        for (int dir : dirArr) {
            newMap[y][x].add(new FireBall(y, x, newWeight, newSpeed, dir));
        }
    }

    static int getSpeed(List<FireBall> input) {
        return input.stream().mapToInt(fireBall -> fireBall.speed).sum() / input.size();
    }

    static int getWeight(List<FireBall> input) {
        return input.stream().mapToInt(fireBall -> fireBall.m).sum() / 5;
    }

    static int[] defSeparateDIr(List<FireBall> input) {
        int def = input.get(0).dir % 2;
        for (FireBall fireBall : input) {
            if ((fireBall.dir % 2) != def) {
                return new int[]{1, 3, 5, 7};
            }
        }
        return new int[]{0, 2, 4, 6};
    }


    static class FireBall {
        int y;
        int x;
        int m;
        int speed;
        int dir;

        public FireBall(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.speed = s;
            this.dir = d;
        }

        public void moveNext() {
            y = (y + dy[dir] * speed);
            x = (x + dx[dir] * speed);

            y = (y + N*300) % N;
            x = (x + N*300) % N;

            newMap[y][x].add(new FireBall(y, x, m, speed, dir));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FireBall fireBall = (FireBall) o;
            return y == fireBall.y && x == fireBall.x && m == fireBall.m && speed == fireBall.speed && dir == fireBall.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x, m, speed, dir);
        }

        @Override
        public String toString() {
            return "FireBall{" +
                    "y=" + y +
                    ", x=" + x +
                    ", m=" + m +
                    ", speed=" + speed +
                    ", dir=" + dir +
                    '}';
        }
    }
}
