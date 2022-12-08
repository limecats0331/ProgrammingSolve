import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static Deque<Integer> deque;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        deque = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().trim().split(" ");
            if (input.length == 2) { //push_front & push_back
                if (input[0].equals("push_front")) {
                    deque.addFirst(Integer.parseInt(input[1]));
                } else {
                    deque.addLast(Integer.parseInt(input[1]));
                }
            } else {
                extracted(input);
            }
        }
    }

    private static void extracted(String[] input) {
        int out = -1;
        switch (input[0]) {
            case "pop_front":
                out = deque.isEmpty() ? -1 : deque.pollFirst();
                System.out.println(out);
                break;
            case "pop_back":
                out = deque.isEmpty() ? -1 : deque.pollLast();
                System.out.println(out);
                break;
            case "size":
                System.out.println(deque.size());
                break;
            case "empty":
                System.out.println(deque.isEmpty() ? 1 : 0);
                break;
            case "front":
                out = deque.isEmpty() ? -1 : deque.peekFirst();
                System.out.println(out);
                break;
            case "back":
                out = deque.isEmpty() ? -1 : deque.peekLast();
                System.out.println(out);
                break;
        }
    }
}
