import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    static Pipe[][] board;
    static int N;
    static int M;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] isVisit;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] info = br.readLine().trim().split(" ");
            N = Integer.parseInt(info[0]);
            M = Integer.parseInt(info[1]);
            int R = Integer.parseInt(info[2]);
            int C = Integer.parseInt(info[3]);
            int L = Integer.parseInt(info[4]);


            board = new Pipe[N][M];
            isVisit = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().trim().split(" ");
                for (int j = 0; j < M; j++) {
                    int type = Integer.parseInt(line[j]);

                    board[i][j] = type == 0 ? null : new Pipe(type);
                }
            }

            if (L == 1) {
                System.out.printf("#%d %d\n", t, 1);
                continue;
            }
            
            bfs(R, C, L);

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (isVisit[i][j]) cnt += 1;
                }
            }
            System.out.printf("#%d %d\n", t, cnt);
        }
    }

    static void bfs(int R, int C, int L) {
//        System.out.printf("R : %d, C : %d, L : %d\n", R, C, L);
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{R, C, 1});

        while (!que.isEmpty()) {
            int[] now = que.poll();
//            System.out.println(Arrays.toString(now));
            int y = now[0];
            int x = now[1];
            int cnt = now[2];
            if (cnt == L) break;

            for (int i = 0; i < 4; i++) {
                if (!board[y][x].open[i]) continue;
                int ny = y + dy[i];
                int nx = x + dx[i];
//                System.out.printf("ny = %d, nx = %d\n", ny, nx);

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (board[ny][nx] == null) continue;

                if (board[ny][nx].isConnect(i) && !isVisit[ny][nx]) {
//                    System.out.println("connect");
                    que.add(new int[]{ny, nx, cnt + 1});
                    isVisit[ny][nx] = true;
                }
            }
        }
    }

    static class Pipe {
        int type;
        //상우하좌
        boolean[] open;

        public Pipe(int type) {
            this.type = type;
            switch (type) {
                case 1:
                    open = new boolean[]{true, true, true, true};
                    break;
                case 2:
                    open = new boolean[]{true, false, true, false};
                    break;
                case 3:
                    open = new boolean[]{false, true, false, true};
                    break;
                case 4:
                    open = new boolean[]{true, true, false, false};
                    break;
                case 5:
                    open = new boolean[]{false, true, true, false};
                    break;
                case 6:
                    open = new boolean[]{false, false, true, true};
                    break;
                case 7:
                    open = new boolean[]{true, false, false, true};
                    break;
            }
        }

        boolean isConnect(int location) {
            int opposite = (location + 2) % 4;
            return this.open[opposite];
        }
    }
}
