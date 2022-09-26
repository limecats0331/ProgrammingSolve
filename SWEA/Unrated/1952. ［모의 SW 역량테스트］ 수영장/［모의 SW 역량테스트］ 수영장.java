import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int[] prices;
    static int[] months;
    static int min;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < T; t++) {
            prices = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            months = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

            min = Integer.MAX_VALUE;

            backTrack(0, 0);
            System.out.printf("#%d %d\n", t + 1, min);
        }


    }

    static void backTrack(int now, int total) {
        if (now >= 12) {
            min = Math.min(min, total);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                backTrack(now + 1, total + prices[i] * months[now]);
            } else if (i == 1) {
                backTrack(now + 1, total + prices[i]);
            } else if (i == 2) {
                backTrack(now + 3, total + prices[i]);
            } else if (i == 3) {
                backTrack(now + 12, total + prices[i]);
            }

        }
    }
}
