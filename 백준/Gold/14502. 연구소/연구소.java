import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<int[][]> wallPlantList = new ArrayList<>();
    static int[][] wallFind;
    static boolean[] isSelect;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");

        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        int[][] map = new int[R][C];

        List<int[]> virus = new ArrayList<>();
        List<int[]> blank = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            input = br.readLine().trim().split(" ");
            for (int j = 0; j < C; j++) {
                if (Integer.parseInt(input[j]) == 2) virus.add(new int[]{i, j});
                else if (Integer.parseInt(input[j]) == 0) blank.add(new int[]{i, j});

                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        wallFind = new int[3][2];
        isSelect = new boolean[blank.size()];

        combi(blank, 0, 0);

        int max = Integer.MIN_VALUE;

        for (int[][] list : wallPlantList) {
            int[][] tmpMap = new int[R][C];

            for (int i = 0; i < R; i++) {
                tmpMap[i] = map[i].clone();
            }

            for (int i = 0; i < 3; i++) {
                tmpMap[list[i][0]][list[i][1]] = 1;
            }

            tmpMap = bfs(tmpMap, virus);
            max = Math.max(max, countSafe(tmpMap));
        }

        System.out.println(max);
    }

    static void showMap(int[][] map) {
        System.out.println("<========each=========>");
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static int countSafe(int[][] map) {
        int result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) result += 1;
            }
        }
        return result;
    }

    static int[][] bfs(int[][] tmpMap, List<int[]> virus) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        Queue<int[]> que = new ArrayDeque<>(virus);
        while (!que.isEmpty()) {
            int[] now = que.poll();
            for (int i = 0; i < 4; i++) {
                int y = now[0] + dy[i];
                int x = now[1] + dx[i];
                if (y < 0 || y >= tmpMap.length || x < 0 || x >= tmpMap[0].length) continue;
                if (tmpMap[y][x] == 0) {
                    que.add(new int[]{y, x});
                    tmpMap[y][x] = 2;
                }
            }
        }
        return tmpMap;
    }

    static void combi(List<int[]> blank, int depth, int start) {
        if (depth == 3) {
            wallPlantList.add(wallFind.clone());
            return;
        }
        for (int i = start; i < blank.size(); i++) {
            if (!isSelect[i]) {
                isSelect[i] = true;
                wallFind[depth] = blank.get(i);

                combi(blank, depth + 1, i + 1);

                isSelect[i] = false;
            }
        }
    }
}
