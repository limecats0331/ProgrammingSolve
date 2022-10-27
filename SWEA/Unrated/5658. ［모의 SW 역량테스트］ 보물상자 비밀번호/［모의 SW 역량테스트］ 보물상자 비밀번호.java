import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int K;
    static List<Character> passwords;
    static Set<Integer> passwordSet;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);

            char[] line = br.readLine().trim().toCharArray();
            passwords = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                passwords.add(line[i]);
            }

            passwordSet = new HashSet<>();

            for (int i = 0; i < N/4; i++) {
                String[] passwordList = splitPasswords();
                for (String each : passwordList) {
                    passwordSet.add(hexadecimalToDecimal(each));
                }
                rotate();
            }
//            System.out.println(passwordSet);

            List<Integer> tmp = new ArrayList<>(passwordSet);
            tmp.sort((a, b) -> -Integer.compare(a, b));
            int answer = tmp.get(K - 1);

//            int answer=0;

            System.out.printf("#%d %d\n", t, answer);

        }
    }

    static void rotate() {
        passwords.add(passwords.remove(0));
    }

    static String[] splitPasswords() {
        String[] result = new String[4];
        int len = N / 4;
        for (int i = 0; i < 4; i++) {
            char[] tmp = new char[len];
            for (int j = len * i; j < len * (i + 1); j++) {
                tmp[j - (len * i)] = passwords.get(j);
            }
            result[i] = String.valueOf(tmp);
        }
        return result;
    }

    static int hexadecimalToDecimal(String input) {
        int result = 0;
        Map<Character, Integer> num = new HashMap<>();
        num.put('A', 10);
        num.put('B', 11);
        num.put('C', 12);
        num.put('D', 13);
        num.put('E', 14);
        num.put('F', 15);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ('A' <= c && c <= 'F') {
                result += Math.pow(16, input.length() - 1 - i) * num.get(c);
            } else {
                result += Math.pow(16, input.length() - 1 - i) * (c - '0');
            }
        }
        return result;
    }

}
