import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine().trim());
        }

        System.out.println(Math.round(average()));
        System.out.println(middle());
        System.out.println(frequency());
        System.out.println(range());
    }

    static double average() {
        return Arrays.stream(numbers).average().getAsDouble();
    }

    static int middle() {
        int[] sortedArray = Arrays.stream(numbers).sorted().toArray();
        return sortedArray[(N / 2)];
    }

    static int frequency() {
        Map<Integer, Integer> freq = new HashMap<>();
        Arrays.stream(numbers)
                .forEach(num -> freq.merge(num, 1, Integer::sum));

        Integer max = freq.values().stream()
                .max((a, b) -> Integer.compare(a, b)).get();

        int[] maxFreq = freq.keySet().stream()
                .filter(key -> freq.get(key) == max)
                .mapToInt(key -> key)
                .sorted()
                .toArray();
        if (maxFreq.length == 1) {
            return maxFreq[0];
        }
        return maxFreq[1];
    }

    static int range() {
        int min = Arrays.stream(numbers).min().getAsInt();
        int max = Arrays.stream(numbers).max().getAsInt();

        return max - min;
    }
}
