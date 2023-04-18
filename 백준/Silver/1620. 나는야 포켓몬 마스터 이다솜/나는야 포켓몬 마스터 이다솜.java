import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int N;
    static Map<String, Integer> nameToNum = new HashMap<>();
    static Map<Integer, String> numToName = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for (int i = 1; i <= N; i++) {
            String name = br.readLine().trim();
            nameToNum.put(name, i);
            numToName.put(i, name);
        }

        for (int i = 0; i < M; i++) {
            String question = br.readLine().trim();
            if (Character.isDigit(question.charAt(0))) {
                System.out.println(numToName.get(Integer.parseInt(question)));
            } else {
                System.out.println(nameToNum.get(question));
            }
        }
    }
}
