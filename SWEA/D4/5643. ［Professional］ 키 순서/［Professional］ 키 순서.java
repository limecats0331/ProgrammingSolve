import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static final int MAX = 10000;

    public static void main(String[] args) throws Exception {
       // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            String[] input;

            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());

            int[][] map = new int[N + 1][N + 1];

            for (int i = 0; i < N + 1; i++) {
                Arrays.fill(map[i], MAX);
                map[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                input = br.readLine().trim().split(" ");
                int from = Integer.parseInt(input[0]);
                int to = Integer.parseInt(input[1]);
                map[from][to] = 1;
            }

            for (int i = 0; i < N + 1; i++) { // route
                for (int j = 0; j < N + 1; j++) { // from
                    for (int k = 0; k < N + 1; k++) { // to
                        map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                    }
                }
            }

            int cnt = 0;
            for (int i = 1; i < N + 1; i++) {
                int con = 0;
                for (int j = 1; j < N + 1; j++) {
                    if (map[i][j] != 0 && map[i][j] != MAX) {
                        con += 1;
                    }
                }

                for (int j = 1; j < N + 1; j++) {
                    if (map[j][i] != 0 && map[j][i] != MAX) {
                        con += 1;
                    }
                }

                if (con == N - 1) {
                    cnt += 1;
                }
            }

            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
