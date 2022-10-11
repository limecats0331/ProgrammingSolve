import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N;
    static int d;
    static int k;
    static int c;
    static int[] belt;
    static Map<Integer, Integer> pick = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        c = Integer.parseInt(input[3]);

        belt = new int[N];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine().trim());
        }

        pick.put(c, 1);
        for (int i = 0; i < k; i++) {
            pick.merge(belt[i], 1, Integer::sum);
        }
//        System.out.println(Arrays.toString(belt));
//        System.out.println(pick);

        int max = pick.size();
        for (int i = 0; i < N; i++) {
            if (pick.get(belt[i]) == 1) {
                pick.remove(belt[i]);
            } else {
                pick.put(belt[i], pick.get(belt[i]) - 1);
            }

            int next = (i+k)%N;
            pick.merge(belt[next], 1,Integer::sum);

            max = Math.max(max, pick.size());
        }

        System.out.println(max);
    }
}
