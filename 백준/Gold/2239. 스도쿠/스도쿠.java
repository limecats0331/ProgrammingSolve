import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        int countZero = 0;

        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().trim().split("");
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 0) countZero += 1;
            }
        }

        sudoku(countZero, false);
    }

    static void showMap(int[][] map) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(String.valueOf(map[i][j]));
            }
            bw.write("\n");
        }
        bw.flush();
    }

    static boolean sudoku(int countZero, boolean isEnd) throws Exception {
//        System.out.println("countZero = " + countZero);
        if (countZero == 0) {
            showMap(map);
            return true;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (checkColumn(j, k) && checkRow(i, k) && checkSubBoard(i, j, k)) {
                            map[i][j] = k;
                            isEnd = sudoku(countZero - 1, isEnd);
                            if (isEnd) break;
                            map[i][j] = 0;
                        }
                    }
                }
                if (isEnd) break;
                if (map[i][j] == 0) return false;
            }
            if (isEnd) break;
        }
        return true;
    }

    static boolean checkSubBoard(int y, int x, int num) {
        int startY = (y / 3) * 3;
        int startX = (x / 3) * 3;
        for (int i = startY; i < startY + 3; i++) {
            for (int j = startX; j < startX + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }
        return true;
    }

    static boolean checkColumn(int line, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[i][line] == num) return false;
        }
        return true;
    }

    static boolean checkRow(int line, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[line][i] == num) return false;
        }
        return true;
    }
}
