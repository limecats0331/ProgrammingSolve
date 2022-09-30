import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] result = new int[12];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        result[0] = 1;
        result[1] = 1;

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            System.out.println(answer(N));
        }

    }

    static int answer(int num) {
        if (result[num] != 0) return result[num];
        else {
            for (int i = 1; i <= 3; i++) {
                if (num - i >= 0) result[num] += answer(num - i);
            }
            return result[num];
        }
    }
}
