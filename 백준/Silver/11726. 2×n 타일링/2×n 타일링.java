import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static long[] fivo = new long[1001];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        fivo[1] = 1;
        fivo[2] = 2;

        System.out.println(answer(N));
    }

    static long answer(int num) {
        if (fivo[num] != 0) return fivo[num];
        else {
            fivo[num] = (answer(num - 1) + answer(num - 2)) % 10007;
            return fivo[num];
        }
    }
}
