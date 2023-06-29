import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] result = new int[2];

    public static void main(String[] args) throws Exception {
       // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        int[][] paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            paper[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        countPaper(paper);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    static void countPaper(int[][] map) {
        int count = allSame(map);
        if (count >= 0) {
            result[count] += 1;
            return;
        }
        for (int[][] sub : splitPaper(map)) {
            countPaper(sub);
        }
    }

    static int[][][] splitPaper(int[][] map) {
        int subLen = map.length / 2;
        int[][][] result = new int[4][subLen][subLen];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                result[(i / subLen) * 2 + (j / subLen)][i % subLen][j % subLen] = map[i][j];
            }
        }
        return result;
    }

    static int allSame(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != map[0][0]) {
                    return -1;
                }
            }
        }
        return map[0][0];
    }

    static void showSub(int[][][] sub) {
        for (int[][] m : sub) {
            System.out.println("sub");
            for (int[] line : m) {
                System.out.println(Arrays.toString(line));
            }
        }
    }
}
