import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int start;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            start = i;
            dfs(i, new boolean[N], 0, 0);
        }

        System.out.println(result);
    }

    static void dfs(int from, boolean[] isVisit, int cnt, int res) {
        if (cnt == N - 1) {
            if(map[from][start] == 0){
                return;
            }
            result = Math.min(result, res + map[from][start]);
            return;
        }

        isVisit[from] = true;
        for (int i = 0; i < N; i++) {
            if (!isVisit[i] && map[from][i] != 0) {
                isVisit[i] = true;

                dfs(i, isVisit, cnt + 1, res + map[from][i]);

                isVisit[i] = false;
            }
        }
        isVisit[from] = false;
    }
}