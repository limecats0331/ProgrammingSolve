import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int H;
    static int W;
    static int K;
    static int[][] map;
    static int min;
    static int[] filter;

    public static void main(String[] args) throws Exception {
       // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);

            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            min = Integer.MAX_VALUE;
            filter = new int[H];

            findFilter(0, 0);

//            boolean pass = pass();
            System.out.printf("#%d %d\n", t, min);
        }
    }

    static void findFilter(int depth, int cnt) {
        if (cnt >= min) {
            return;
        }
        if (depth == H) {
            if (pass()) {
                min = Math.min(cnt, min);
            }
            return;
        }

        filter[depth] = 0;
        findFilter(depth + 1, cnt);
        filter[depth] = 1;
        findFilter(depth + 1, cnt + 1);
        filter[depth] = 2;
        findFilter(depth + 1, cnt + 1);
    }

    static boolean pass() {
        for (int i = 0; i < W; i++) {
            if (!defEachLine(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean defEachLine(int w) {
        int prev = filtering(0, map[0][w]);
        int cnt = 1;
        int max = 0;
        for (int i = 1; i < H; i++) {
            int next = filtering(i, map[i][w]);
            if (prev == next) {
                cnt += 1;
            } else {
                max = Math.max(max, cnt);
                cnt = 1;
            }
            prev = next;
        }
        max = Math.max(max, cnt);

        return max >= K;
    }

    static int filtering(int h, int cnt) {
        if (filter[h] == 0) {
            return cnt;
        } else if (filter[h] == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
