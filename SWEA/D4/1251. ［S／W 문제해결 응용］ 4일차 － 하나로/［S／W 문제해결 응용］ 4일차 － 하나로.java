import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    static List<int[]> islands = new ArrayList<>();
    static int[] root;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            islands = new ArrayList<>();

            String[] inputX = br.readLine().trim().split(" ");
            String[] inputY = br.readLine().trim().split(" ");

            //각 섬에 대한 좌료를 저장
            for (int i = 0; i < N; i++) {
                islands.add(new int[]{Integer.parseInt(inputX[i]), Integer.parseInt(inputY[i])});
            }
            double tax = Double.parseDouble(br.readLine().trim());

            // 크루스칼을 사용하기 위한 준비
            int size = islands.size();
            root = new int[size + 1];
            for (int i = 0; i < size + 1; i++) {
                root[i] = i;
            }
            PriorityQueue<Load> pq = new PriorityQueue<>();

            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    double dist = getDistance(islands.get(i), islands.get(j));
                    double weight = Math.pow(dist, 2) * tax;
                    pq.add(new Load(i, j, weight));
                }
            }

            //여기서부터 크루스칼 사용
            int cnt = 0;
            double res = 0;
            while (!pq.isEmpty()) {
                Load load = pq.poll();
                if (unionSet(load.from, load.to)) {
                    res += load.weight;
                    cnt += 1;
                }
                if (cnt == size - 1) {
                    break;
                }
            }

            System.out.println("#" + t + " " + Math.round(res));
        }
    }

    static boolean unionSet(int from, int to) {
        from = findRoot(from);
        to = findRoot(to);
        if (from == to) return false;
        root[from] = to;
        return true;
    }

    static int findRoot(int n) {
        if (root[n] == n) return n;
        return findRoot(root[n]);
    }

    static double getDistance(int[] island1, int[] island2) {
        return Math.sqrt(Math.pow((island1[0] - island2[0]), 2) + Math.pow((island1[1] - island2[1]), 2));
    }

    static class Load implements Comparable<Load> {
        int from;
        int to;
        double weight;

        public Load(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Load l) {
            return Double.compare(this.weight, l.weight);
        }

        @Override
        public String toString() {
            return "Load{" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }
    }
}
