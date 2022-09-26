import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int[] operCnt;
    static int[] numbers;
    static int N;
    static int min;
    static int max;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            operCnt = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            numbers = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
//            System.out.println("N = " + N);

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            dfs(0, operCnt, numbers[0]);

//            System.out.println("min = " + min);
//            System.out.println("max = " + max);
            System.out.printf("#%d %d\n", t + 1, max - min);
        }
    }

    static void dfs(int now, int[] cnt, int sum) {
//        System.out.println("cnt = " + Arrays.toString(cnt));
        if (now == N - 1) {
//            System.out.println("sum = " + sum);
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        if (cnt[0] > 0) {
            cnt[0] -= 1;
            sum += numbers[now + 1];
            dfs(now + 1, cnt, sum);
            cnt[0] += 1;
            sum -= numbers[now + 1];
        }
        if (cnt[1] > 0) {
            cnt[1] -= 1;
            sum -= numbers[now + 1];
            dfs(now + 1, cnt, sum);
            cnt[1] += 1;
            sum += numbers[now + 1];
        }
        if (cnt[2] > 0) {
            cnt[2] -= 1;
            sum *= numbers[now + 1];
            dfs(now + 1, cnt, sum);
            cnt[2] += 1;
            sum /= numbers[now + 1];
        }
        if (cnt[3] > 0) {
            cnt[3] -= 1;
            sum /= numbers[now + 1];
            dfs(now + 1, cnt, sum);
            cnt[3] += 1;
            sum *= numbers[now + 1];
        }
    }
}
