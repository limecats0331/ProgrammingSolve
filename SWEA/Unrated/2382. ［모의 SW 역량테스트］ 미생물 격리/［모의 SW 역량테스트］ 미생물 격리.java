import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {
    static int N;
    static int M;
    static int K;
    static Virus[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().trim().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);

            map = new Virus[N][N];
            for (int i = 0; i < K; i++) {
                input = br.readLine().trim().split(" ");
                int y = Integer.parseInt(input[0]);
                int x = Integer.parseInt(input[1]);
                int size = Integer.parseInt(input[2]);
                int dir = Integer.parseInt(input[3]);
                map[y][x] = new Virus(y, x, size, dir - 1);
            }

            //매 시간 반복;
            for (int i = 1; i <= M; i++) {
                Virus[][] tmp = new Virus[N][N];
                List<Sum> sumList = new ArrayList<>();

                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (map[j][k] != null) {
                            map[j][k].move();
                            Virus moved = map[j][k];
                            if (moved.size == 0) continue;
                            if (tmp[moved.y][moved.x] == null) {
                                tmp[moved.y][moved.x] = moved;
                            } else { //중복이면
                                Sum s = new Sum(moved.y, moved.x);
                                if (sumList.contains(s)) {
                                    sumList.get(sumList.indexOf(s)).list.add(moved);
                                } else {
                                    s.list.add(moved);
                                    s.list.add(tmp[moved.y][moved.x]);
                                    sumList.add(s);
                                }
                            }
                        }
                    }
                }

                for (Sum sum : sumList) {
                    Virus result = sum.sumResult();
                    tmp[result.y][result.x] = result;
                }

                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        map[j][k] = tmp[j][k];
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != null) {
                        result += map[i][j].size;
                    }
                }
            }

            System.out.printf("#%d %d\n", t, result);
        }
    }

    static void showMap() {
        System.out.println("show map");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) {
                    System.out.print("0\t");
                } else {
                    System.out.print(map[i][j].dir + ":" + map[i][j].size + "\t");
                }
            }
            System.out.println();
        }
    }

    static class Sum {
        int y;
        int x;
        List<Virus> list;

        public Sum(int y, int x) {
            this.y = y;
            this.x = x;
            list = new ArrayList<>();
        }

        public Virus sumResult() {
            Virus result = new Virus(y, x, 0, 0);

            int size = 0;
            int dir = 0;
            int max = 0;
            for (Virus each : list) {
                size += each.size;
                if (max < each.size) {
                    max = each.size;
                    dir = each.dir;
                }
            }
            result.dir = dir;
            result.size = size;
//            System.out.println(result);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Sum sum = (Sum) o;
            return y == sum.y && x == sum.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static class Virus {
        int y;
        int x;
        int size;
        int dir;

        public Virus(int y, int x, int size, int dir) {
            this.y = y;
            this.x = x;
            this.size = size;
            this.dir = dir;
        }

        private void changeDir() {
            if (dir == 0) dir = 1;
            else if (dir == 1) dir = 0;
            else if (dir == 2) dir = 3;
            else if (dir == 3) dir = 2;
        }

        public void move() {
            y = y + dy[dir];
            x = x + dx[dir];
            if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
                if (size == 1) size = 0;
                else size = Math.round(size / 2);

                changeDir();
            }
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "y=" + y +
                    ", x=" + x +
                    ", size=" + size +
                    ", dir=" + dir +
                    '}';
        }

        public void addSize(int size) {
            this.size += size;
        }
    }
}
