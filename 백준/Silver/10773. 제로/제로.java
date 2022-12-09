import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine().trim());

        Stack<Integer> numbers = new Stack<>();
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine().trim());

            if (num != 0) {
                numbers.add(num);
            } else {
                numbers.pop();
            }
        }

        System.out.println(numbers.stream().reduce((a, b) -> a + b).orElse(0));
    }
}
