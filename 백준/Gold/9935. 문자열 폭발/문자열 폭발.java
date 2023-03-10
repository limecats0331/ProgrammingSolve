import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static String str;
    static String boom;
    static List<Character> result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine().trim();
        boom = br.readLine().trim();

        result = new ArrayList<>();

        for (char c : str.toCharArray()) {
            result.add(c);
            if (c == boom.charAt(boom.length() - 1)) {
                if (define()) {
                    remove();
                }
            }
        }

        if (result.isEmpty()) {
            System.out.println("FRULA");
            return;
        }
        
        String tmp = result.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(tmp);
    }

    static void remove() {
        int size = result.size();
        for (int i = size - 1; i > size - 1 - boom.length(); i--) {
            result.remove(result.size() - 1);
        }
    }

    static boolean define() {
        if (result.size() < boom.length()) return false;
        for (int i = 0; i < boom.length(); i++) {
            if (result.get(result.size() - 1 - i) != boom.charAt(boom.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
