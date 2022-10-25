import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static List<Node>[] graph;
    static int[] costs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());

        graph = new List[N + 1];
        costs = new int[N + 1];
        Arrays.fill(costs, 99999999);

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().trim().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);
            graph[from].add(new Node(to, cost));
        }

        String[] input = br.readLine().trim().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        dijkstra(start);

        System.out.println(costs[end]);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        costs[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.city;
            int cost = node.cost;

            if (costs[now] < cost) continue;

            for (int i = 0; i < graph[now].size(); i++) {
                Node next = graph[now].get(i);

                int newCost = costs[now] + next.cost;
                if (newCost < costs[next.city]) {
                    costs[next.city] = newCost;
                    pq.add(new Node(next.city, newCost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int city;
        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}
