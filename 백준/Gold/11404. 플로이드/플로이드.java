import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static final int MAX = 1000000000;
    static int city;
    static int busCnt;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        city = Integer.parseInt(br.readLine().trim());
        busCnt = Integer.parseInt(br.readLine().trim());

        map = new int[city + 1][city + 1];

        for (int i = 0; i < city + 1; i++) {
            Arrays.fill(map[i], MAX);
            map[i][i] = 0;
        }

        for (int i = 0; i < busCnt; i++) {
            String[] input = br.readLine().trim().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            if (map[from][to] > weight) {
                map[from][to] = weight;
            }
        }

        for (int i = 1; i < city + 1; i++) { // route
            for (int j = 1; j < city + 1; j++) { // from
                for (int k = 1; k < city + 1; k++) { // to
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }

        for (int i = 1; i < city + 1; i++) {
            for (int j = 1; j < city + 1; j++) {
                bw.write(map[i][j] == MAX ? "0" : String.valueOf(map[i][j]));
//                bw.write(String.valueOf(map[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
