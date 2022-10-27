import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().trim().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] depth = new int[N + 1];

        List<Integer>[] graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().trim().split(" ");
            int first = Integer.parseInt(input[0]);
            int next = Integer.parseInt(input[1]);

            graph[first].add(next);
            depth[next] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++) {
            if (depth[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            bw.write(Integer.toString(now));
            bw.write(" ");
            for (int n : graph[now]) {
                depth[n] -= 1;
                if (depth[n] == 0) {
                    pq.add(n);
                }
            }
        }
        bw.flush();
    }
}
