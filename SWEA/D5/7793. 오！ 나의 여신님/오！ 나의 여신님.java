import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    static int N;
    static int M;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            map = new char[N][M];

            int[] suyun = new int[2];
            List<int[]> devil = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().trim().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 'S') {
                        suyun = new int[]{i, j};
                    } else if (map[i][j] == '*') {
                        devil.add(new int[]{i, j});
                    }
                }
            }

            int result = bfs(suyun, devil);
            if (result == -1) {
                System.out.printf("#%d GAME OVER\n", t);
            } else {
                System.out.printf("#%d %d\n", t, result);
            }
        }
    }

    static int bfs(int[] people, List<int[]> devil) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{people[0], people[1], 0});
        for (int[] d : devil) {
            queue.add(d);
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                //벗어나면 패스
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }
                //벽이면 패스
                if (map[ny][nx] == 'X') {
                    continue;
                }

                //만약 수연이면
                if (map[now[0]][now[1]] == 'S') {
                    if (map[ny][nx] == 'S') {
                        continue;
                    }
                    if (map[ny][nx] == '.') {
                        queue.add(new int[]{ny, nx, now[2] + 1});
                        map[ny][nx] = 'S';
                    } else if (map[ny][nx] == 'D') {
                        return now[2] + 1;
                    }
                } else if (map[now[0]][now[1]] == '*') {//만약 악마면
                    if (map[ny][nx] == '*') {
                        continue;
                    }
                    if (map[ny][nx] != 'D') {
                        queue.add(new int[]{ny, nx, 0});
                        map[ny][nx] = '*';
                    }
                }
            }
        }
        return -1;
    }
}
