import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int result = 1;
        for (int i = 1; i < N + 1; i++) {
            if (i % 2 == 0) {
                result = result * 2 + 1;
            } else {
                result = result * 2 - 1;
            }
            result %= 10007;
        }
        System.out.println(result);
    }
}
