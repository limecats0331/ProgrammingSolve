import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int H;
    static int W;
    static int L;
    static Pipe[][] map;
    static boolean[][] isVisit;
    //상우하좌
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            int R = Integer.parseInt(input[2]);
            int C = Integer.parseInt(input[3]);
            L = Integer.parseInt(input[4]);

            map = new Pipe[H][W];

            for (int i = 0; i < H; i++) {
                int[] line = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < W; j++) {
                    if (line[j] != 0) {
                        map[i][j] = new Pipe(line[j]);
                    }
                }
            }

            isVisit = new boolean[H][W];
            bfs(R, C);
            System.out.printf("#%d %d\n", t, getResult());
        }
    }

    static int getResult() {
        int result = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (isVisit[i][j]) result++;
            }
        }
        return result;
    }

    static void bfs(int y, int x) {
        isVisit[y][x] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x, 1});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[2] == L) break;

            for (int i = 0; i < 4; i++) {
                if(!map[now[0]][now[1]].connected[i]) continue;
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
                if (map[ny][nx] == null) continue;

                if (!isVisit[ny][nx] && map[ny][nx].isConnect(i)) {
                    queue.add(new int[]{ny, nx, now[2] + 1});
                    isVisit[ny][nx] = true;
                }
            }
        }
    }

    static class Pipe {
        int type;
        boolean[] connected;
        Map<Integer, boolean[]> pipeType = new HashMap<Integer, boolean[]>() {{
            put(1, new boolean[]{true, true, true, true});
            put(2, new boolean[]{true, false, true, false});
            put(3, new boolean[]{false, true, false, true});
            put(4, new boolean[]{true, true, false, false});
            put(5, new boolean[]{false, true, true, false});
            put(6, new boolean[]{false, false, true, true});
            put(7, new boolean[]{true, false, false, true});
        }};

        public Pipe(int type) {
            this.type = type;
            this.connected = pipeType.get(type);
        }

        public boolean isConnect(int dir) {
            return connected[(dir + 2) % 4];
        }
    }
}
