import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int M;
    //    static Node[] nodes;
    static int[][] locations;
    static int[] root;

    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        locations = new int[N + 1][2];
//        nodes = new Node[N + 1];

        for (int i = 1; i < N + 1; i++) {
            input = br.readLine().trim().split(" ");
            locations[i] = new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])};
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                pq.add(new Node(i, j));
            }
        }

        root = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            root[i] = i;
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            input = br.readLine().trim().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            unionSet(from, to);

            cnt++;
        }


        double res = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
//            System.out.println("now = " + now);
//            System.out.println(Arrays.toString(root));

            if (unionSet(now.from, now.to)) {
                cnt += 1;
                res += now.weight;
            }
            if (cnt == N - 1) {
                break;
            }
        }

        System.out.printf("%.2f", res);
    }

    static boolean unionSet(int from, int to) {
        from = findRoot(from);
        to = findRoot(to);

        if (from == to) {
            return false;
        }
        root[from] = to;
        return true;
    }

    static int findRoot(int num) {
        if (root[num] == num) {
            return num;
        }
        return findRoot(root[num]);
    }

    static double getDistance(int fromNum, int toNum) {
        int[] from = locations[fromNum];
        int[] to = locations[toNum];
        return Math.sqrt(
                Math.pow(Math.abs(from[0] - to[0]), 2) +
                        Math.pow(Math.abs(from[1] - to[1]), 2));
    }

    static class Node implements Comparable<Node> {
        int from;
        int to;
        double weight;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
            this.weight = getDistance(from, to);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node n) {
            return Double.compare(this.weight, n.weight);
        }
    }
}
