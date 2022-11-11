import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int N;
    static int B;
    static int[] H;
    static int result;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            N = Integer.parseInt(input[0]);
            B = Integer.parseInt(input[1]);

            H = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

            result = Integer.MAX_VALUE;

            dfs(0, 0);

            System.out.printf("#%d %d\n", t, result - B);
        }
    }

    static void dfs(int depth, int h) {
        if (depth == N) {
            if (h >= B) {
                result = Math.min(result, h);
            }
            return;
        }

        dfs(depth + 1, h);
        dfs(depth + 1, h + H[depth]);
    }
}
