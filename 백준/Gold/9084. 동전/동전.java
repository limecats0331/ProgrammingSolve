import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class Main {
    static int N;
    static int[] moneyArray;
    static int money;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            moneyArray = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            money = Integer.parseInt(br.readLine().trim());

            dp = new int[money + 1];
            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                for (int j = moneyArray[i]; j <= money; j++) {
                    dp[j] += dp[j - moneyArray[i]];
                }
            }

            System.out.println(dp[money]);
        }
    }

}
