import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] child;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        child = new int[N];

        for (int i = 0; i < N; i++) {
            child[i] = Integer.parseInt(br.readLine().trim());
        }

        int result = LIS();
        System.out.println(N - result);
    }

    static int LIS() {
        int[] dp = new int[N];
        dp[0] = child[0];
        int dpIdx = 0;

        for (int i = 1; i < N; i++) {
            if (child[i] > dp[dpIdx]) {
                dp[++dpIdx] = child[i];
            } else if (child[i] < dp[dpIdx]) {
                int num = Arrays.binarySearch(dp, 0, dpIdx, child[i]);
                if (num > 0) {
                    dp[num] = child[i];
                } else {
                    dp[-(num + 1)] = child[i];
                }
            }
        }

        return dpIdx + 1;
    }
}
