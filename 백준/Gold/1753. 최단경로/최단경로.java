import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static final int MAX = 100000000;
    static int V;
    static int E;
    static int[][] map;
    static List<Load>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        graph = new List[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < E; i++) {
            input = br.readLine().trim().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            graph[from].add(new Load(from, to, weight));
        }

        int[] result = dijkstra(start);

        for (int i = 1; i < result.length; i++) {
            System.out.println(result[i] == MAX ? "INF" : result[i]);
        }

    }

    static int[] dijkstra(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, MAX);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int now = node[0];
            int distance = node[1];

            if (dist[now] < distance) continue;

            for (Load each : graph[now]) {
                int cost = distance + each.weight;
                int next = each.to;
                if (dist[next] > cost) {
                    dist[next] = cost;
                    pq.add(new int[]{next, cost});
                }
            }
        }
        return dist;
    }

    static class Load implements Comparable<Load> {
        int from;
        int to;
        int weight;

        public Load(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Load l) {
            return Integer.compare(this.weight, l.weight);
        }
    }

}
