import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static boolean[] locations;
    static int from;
    static int to;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().strip().split(" ");
        from = Integer.parseInt(input[0]);
        to = Integer.parseInt(input[1]);
        locations = new boolean[100001];

        int answer = BFS();
        System.out.println(answer);
    }

    public static int BFS() {
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> -Integer.compare(a[1], b[1]));
        Queue<int[]> pq = new ArrayDeque<>();
        pq.add(new int[]{from, 0});
        locations[from] = true;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (now[0] == to) return now[1];

            int[] dLocation = new int[]{now[0], -1, 1};
            int[] dTime = new int[]{0, 1, 1};

            for (int i = 0; i < 3; i++) {
                int next = now[0] + dLocation[i];
                if (next < 0 || next > 100000) continue;
                if (locations[next]) continue;

                pq.add(new int[]{next, now[1] + dTime[i]});
                locations[next] = true;
            }
        }

        return -1;
    }
}
