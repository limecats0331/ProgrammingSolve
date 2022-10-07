import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int H;
    static int W;
    static int[][] map;
    static boolean[][] isVisit;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[] root;
    static PriorityQueue<Bridge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        H = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        map = new int[H][W];
        isVisit = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int color = 1;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 1 && !isVisit[i][j]) {
                    changeIsland(i, j, color);
                    color += 1;
                }
            }
        }

        root = new int[color];
        for (int i = 1; i < root.length; i++) {
            root[i] = i;
        }

        isVisit = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0 && !isVisit[i][j]) {
                    findBridges(i, j, map[i][j]);
                }
            }
        }

//        for (int i = 0; i < H; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }


        int cnt = 0;
        int res = 0;
        boolean none = true;
        while (!pq.isEmpty()) {
            Bridge b = pq.poll();
//            System.out.println("b = " + b);
            if (union(b.from, b.to)) {
//                System.out.println("inserted b = " + b);
                res += b.weight;
                cnt += 1;
            }
            if (cnt == color - 2) {
                none = false;
                break;
            }
        }

        if (none) {
            System.out.println(-1);
        } else {
            System.out.println(res == 0 ? -1 : res);
        }
    }

    static boolean union(int from, int to) {
        from = find(from);
        to = find(to);
        if (from == to) {
            return false;
        }
        root[from] = to;
        return true;
    }

    static int find(int n) {
        if (root[n] == n) {
            return n;
        } else {
            return find(root[n]);
        }
    }

    static void findBridges(int y, int x, int color) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});
        isVisit[y][x] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
                //섬 밖이면서 바다면
                if (map[ny][nx] == 0) {
                    //해당 방향으로 섬이 있는지 탐색
//                    System.out.printf("find location : %d, %d ,color : %d, dir : %d \n", ny, nx, color, i);
                    bridgeInfo(now[0], now[1], i, map[now[0]][now[1]]);
//                    System.out.println("pq = " + pq);
                } else if (map[ny][nx] == color && !isVisit[ny][nx]) {
                    queue.add(new int[]{ny, nx});
                    isVisit[ny][nx] = true;
                }
            }
        }
    }

    static void bridgeInfo(int y, int x, int dir, int color) {
        int cnt = 0;
        for (int i = 1; i < Math.max(H, W); i++) {
            int ny = y + dy[dir] * i;
            int nx = x + dx[dir] * i;
            if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
            if (map[ny][nx] == 0) {
                cnt += 1;
            } else if (map[ny][nx] == color) {
                return;
            } else {
                if (cnt > 1) {
                    Bridge bridge = new Bridge(color, map[ny][nx], cnt);
                    pq.add(bridge);
                }
                return;
            }
        }
    }

    static void changeIsland(int y, int x, int num) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});
        map[y][x] = num;
        isVisit[y][x] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
                if (map[ny][nx] == 1 && !isVisit[ny][nx]) {
                    queue.add(new int[]{ny, nx});
                    isVisit[ny][nx] = true;
                    map[ny][nx] = num;
                }
            }
        }
    }

    static class Bridge implements Comparable<Bridge> {
        int from;
        int to;
        int weight;

        public Bridge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Bridge b) {
            return Integer.compare(this.weight, b.weight);
        }

        @Override
        public String toString() {
            return "bridge{" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }
    }
}

// 1. 섬을 나눈다.
// 2. 섬 별로 번호를 달리한다.
// 3. 섬 별로 들어가서 다리를 건설한다.( -1로 체크)
// 4. 다리를 가지고 조합을 해서 가장 짧은 것을 찾는다.
