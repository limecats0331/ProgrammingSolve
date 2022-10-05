import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int R;
    static int C;
    static int M;
    static Shark[][] map;
    //위 아래 오른쪽 윈쪽
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 1, -1};
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);
        map = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            input = br.readLine().trim().split(" ");
            int r = Integer.parseInt(input[0]) - 1;
            int c = Integer.parseInt(input[1]) - 1;
            int s = Integer.parseInt(input[2]);
            int d = Integer.parseInt(input[3]);
            int z = Integer.parseInt(input[4]);
            map[r][c] = new Shark(r, c, z, d, s);
        }

        //move fisher
        for (int i = 0; i < C; i++) {
//            showMap("show map start");
            fishing(i);
//            showMap("after fishing");
            moveSharks();
//            showMap("show map after move");
        }

        System.out.println(result);
    }

    static void showMap(String msg) {
        Map<Integer, String> show = new HashMap<Integer, String>() {{
            put(1, "^");
            put(2, "v");
            put(3, ">");
            put(4, "<");
        }};
        System.out.println(msg);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == null) System.out.printf("0   \t");
                else System.out.printf("%s%d:%d\t", show.get(map[i][j].direction), map[i][j].speed, map[i][j].size);
            }
            System.out.println();
        }
    }

    //move shark
    static void moveSharks() {
        Shark[][] copyMap = new Shark[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != null) {
                    map[i][j].move();
                    int y = map[i][j].y;
                    int x = map[i][j].x;
                    //상어가 없으면
                    if (copyMap[y][x] == null) {
                        copyMap[y][x] = map[i][j];
                    } else { //상어가 있으면
                        copyMap[y][x] = copyMap[y][x].size > map[i][j].size ? copyMap[y][x] : map[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            map[i] = copyMap[i].clone();
        }
    }


    //fishing
    static void fishing(int line) {
        for (int i = 0; i < R; i++) {
            if (map[i][line] != null) {
                result += map[i][line].size;
                map[i][line] = null;
                return;
            }
        }
    }

    static class Shark {
        public int y;
        public int x;
        public int size;
        public int direction;
        public int speed;


        public Shark(int y, int x, int size, int direction, int speed) {
            this.y = y;
            this.x = x;
            this.size = size;
            this.direction = direction;
            this.speed = speed;
        }

        public void changeDir() {
            switch (direction) {
                case 1:
                    direction = 2;
                    break;
                case 2:
                    direction = 1;
                    break;
                case 3:
                    direction = 4;
                    break;
                case 4:
                    direction = 3;
                    break;
            }
        }

        public void move() {
            if (speed == 0) return;
            int ny, dirY, loY, nx, dirX, loX;
//            System.out.println("y = " + y + " x = " + x + " dir = " + direction);
            switch (direction) {
                case 1: //up
                    ny = y + dy[direction] * speed;
                    if (ny > 0) {
                        y = ny;
                        return;
                    }
                    dirY = (Math.abs(ny) / (R - 1)) + 1;
                    loY = Math.abs(ny) % (R - 1);
//                    System.out.printf("ny : %d, dirY : %d, loY : %d\n", ny, dirY, loY);

                    if (dirY % 2 == 0) {
                        y = (R - 1) - loY;
                    } else {
                        changeDir();
                        y = loY;
                    }
//                    System.out.println("direction = " + direction + " y = " + y);
                    break;
                case 2: //down
                    ny = y + dy[direction] * speed;
                    dirY = ny / (R - 1);
                    loY = ny % (R - 1);
//                    System.out.printf("ny : %d, dirY : %d, loY : %d speed : %d\n", ny, dirY, loY, speed);

                    if (dirY % 2 == 0) {
                        y = loY;
                    } else {
                        changeDir();
                        y = (R - 1) - loY;
                    }
//                    System.out.println("direction = " + direction + " y = " + y);
                    break;
                case 3: //right
                    nx = x + dx[direction] * speed;
                    dirX = nx / (C - 1);
                    loX = nx % (C - 1);
//                    System.out.printf("nx : %d, dirX : %d, loX : %d, speed : %d\n", nx, dirX, loX, speed);

                    if (dirX % 2 == 0) {
                        x = loX;
                    } else {
                        changeDir();
                        x = (C - 1) - loX;
                    }
//                    System.out.println("direction = " + direction + " x = " + x);
                    break;
                case 4: //left
                    nx = x + dx[direction] * speed;
                    if (nx > 0) {
                        x = nx;
                        return;
                    }
                    dirX = (Math.abs(nx) / (C - 1)) + 1;
                    loX = Math.abs(nx) % (C - 1);
//                    System.out.printf("nx : %d, dirX : %d, loX : %d, speed : %d, C : %d\n", nx, dirX, loX, speed, C);

                    if (dirX % 2 == 0) {
                        x = (C - 1) - loX;
                    } else {
                        changeDir();
                        x = loX;
                    }
//                    System.out.println("direction = " + direction + " x = " + x);
                    break;
            }
        }

        @Override
        public String toString() {
            return "Shark{size=" + size + ", direction=" + direction + ", speed=" + speed + '}';
        }
    }
}
