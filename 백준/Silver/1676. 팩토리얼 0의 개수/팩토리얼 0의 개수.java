import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int num = 1;
        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            num *= i;
            num %= 10000;
            while(num % 10 == 0){
                cnt += 1;
                num /= 10;
            }
        }

        System.out.println(cnt);
    }
}
