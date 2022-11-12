import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, String> dirShape = new HashMap<Integer, String>() {{
        put(0, "↑");
        put(1, "↖");
        put(2, "←");
        put(3, "↙");
        put(4, "↓");
        put(5, "↘");
        put(6, "→");
        put(7, "↗");
    }};
    //위부터 시계 반대 방향
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish[][] map = new Fish[4][4];
    static Fish[][] newMap;
    static int eat;
    static int result;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().trim().split(" ");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(input[j * 2]);
                int dir = Integer.parseInt(input[(j * 2) + 1]) - 1;

                map[i][j] = new Fish(num, dir, 0);
            }
        }

        eat = map[0][0].num;
        map[0][0] = new Fish(-1, map[0][0].dir, 1);
        result = Integer.MIN_VALUE;

        DFS(map, 0, 0, map[0][0].dir, eat);

        System.out.println(result);
    }

    static void DFS(Fish[][] prevMap, int sharkY, int sharkX, int dir, int cnt) {
        Fish[][] moveFish = moveFish(prevMap);

        if (!sharkCanMove(moveFish, sharkY, sharkX, dir)) {
            result = Math.max(result, cnt);
            return;
        }

        for (int i = 1; i < 4; i++) { //상어가 갈 수 있는 방향으로 탐색
            Fish[][] newMap = new Fish[4][4];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (moveFish[j][k] == null) newMap[j][k] = null;
                    else newMap[j][k] = new Fish(moveFish[j][k].num, moveFish[j][k].dir, moveFish[j][k].type);
                }
            }

            int ny = sharkY + dy[dir] * i;
            int nx = sharkX + dx[dir] * i;

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) break; //맵을 넘어가게 되면 탐색 X
            if (newMap[ny][nx] == null) continue; //물고기가 없으면 이동 X

            //이동할 수 있는 칸이면 물고기를 먹고 다음 진행
            int eatCnt = newMap[ny][nx].num;
            int nextDir = newMap[ny][nx].dir;

            newMap[sharkY][sharkX] = null;
            newMap[ny][nx] = new Fish(-1, nextDir, 1);

            DFS(newMap, ny, nx, nextDir, cnt + eatCnt);
        }
    }

    static boolean sharkCanMove(Fish[][] map, int y, int x, int dir) {
        for (int i = 1; i < 4; i++) {
            int ny = y + dy[dir] * i;
            int nx = x + dx[dir] * i;

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) break;
            if (map[ny][nx] != null) return true;
        }
        return false;
    }

    static Fish[][] moveFish(Fish[][] map) {
        newMap = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == null) newMap[i][j] = null;
                else newMap[i][j] = new Fish(map[i][j].num, map[i][j].dir, map[i][j].type);
            }
        }

        for (int i = 1; i <= 16; i++) {
            number:
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (newMap[j][k] == null) continue;
                    if (newMap[j][k].num == i) { //해당 번호에 맞으면
                        if (newMap[j][k].canMove(newMap, j, k)) {
                            int dir = newMap[j][k].dir;
                            fishSwap(newMap, j, k, j + dy[dir], k + dx[dir]);
                            break number;
                        }
                    }
                }
            }
        }
        return newMap;
    }

    static void fishSwap(Fish[][] newMap, int fy, int fx, int ty, int tx) {
        Fish tmp = newMap[ty][tx];
        newMap[ty][tx] = newMap[fy][fx];
        newMap[fy][fx] = tmp;
    }

    static void showMap(Fish[][] map) {
        System.out.println("========= show map ============");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == null) {
                    System.out.printf("%d{%s}\t", 0, "_");
                } else {
                    System.out.printf("%d{%s}\t", map[i][j].num, dirShape.get(map[i][j].dir));
                }
            }
            System.out.println();
        }
    }

    static class Fish {
        int num;
        int dir;
        //물고기면 0, 상어면 1
        int type;

        public Fish(int num, int dir, int type) {
            this.num = num;
            this.dir = dir;
            this.type = type;
        }

        boolean canMove(Fish[][] map, int y, int x) {
            for (int i = 0; i < 8; i++) {
                int nextDir = (dir + i) % 8;

                int ny = y + dy[nextDir];
                int nx = x + dx[nextDir];

                if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue; //맵 범위 밖으로 나가면

                if (map[ny][nx] == null) { //물고가가 없으면
                    this.dir = nextDir;
                    return true;
                }

                if (map[ny][nx].type == 1) continue; //상어가 있으면

                this.dir = nextDir;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "num=" + num +
                    ", dir=" + dir +
                    ", type=" + type +
                    '}';
        }
    }
}
