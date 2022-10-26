import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int L;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] isVisit;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = 0;
        //존나 모르겠다.
//        System.out.println("H");
        for (int i = 0; i < N; i++) {
            isVisit = new boolean[N][N];
            if(check(0, i, 0)) {
//                System.out.println(i);
                result ++;
            }
        }
//        showMap();
//        System.out.println("W");
        for (int i = 0; i < N; i++) {
            isVisit = new boolean[N][N];
            if(check(i, 0, 1)) {
//                System.out.println(i);
                result ++;
            }
        }
//        showMap();
        System.out.println(result);

    }
//    static void showMap(){
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.printf("%b\t",isVisit[i][j]);
//            }
//            System.out.println();
//        }
//    }

    /**
     * 1. 도로의 높이가 같을 때
     * 2. 도로의 높이 차이가 1 초과 일때
     * 3. 도로의 높이가 1차이로 올라가고 있을 때
     * 4. 도로의 높이가 1차이로 내려가고 있을 때
     * dir : 0 = 세로, 1 = 가로
     */
    static boolean check(int startY, int startX, int dir) {
        int deltaY = dir == 0 ? 1 : 0;
        int deltaX = dir == 0 ? 0 : 1;

        int y = startY;
        int x = startX;
        for (int i = 0; i < N - 1; i++) {
            int now = map[y + deltaY * i][x + deltaX * i];
            int next = map[y + deltaY * (i + 1)][x + deltaX * (i + 1)];
//            System.out.println("y = " + (y + deltaY * i) + " x = " + (x + deltaX * i) + " now = " + now);
//            System.out.println("y = " + (y + deltaY * (i + 1)) + " x = " + (x + deltaX * (i + 1)) + " next = " + next);
            if (now == next) { // 1. 높이가 같으면
//                System.out.println("num1");
            } else if (Math.abs(now - next) > 1) { //2.높이차이가 1 초과
//                System.out.println("num2");
                return false;
            } else if (now - next == 1) { // 3. 1차이로 내리막길
//                System.out.println("num3");
                int checkDir = dir == 0 ? 2 : 1;
                if (checkVisit(y + deltaY * (i + 1), x + deltaX * (i + 1), checkDir)) {
//                    System.out.println("check1");
                    changeVisit(y + deltaY * (i + 1), x + deltaX * (i + 1), checkDir);
                } else {
                    return false;
                }
            } else if (now - next == -1) { // 4. 1차이로 오르막길
//                System.out.println("num4");
                int checkDir = dir == 0 ? 0 : 3;
                if (checkVisit(y + deltaY * i, x + deltaX * i, checkDir)) {
//                    System.out.println("check2");
                    changeVisit(y + deltaY * i, x + deltaX * i, checkDir);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    static void changeVisit(int y, int x, int dir) {
        for (int i = 0; i < L; i++) {
            isVisit[y + dy[dir] * i][x + dx[dir] * i] = true;
        }
    }

    static boolean checkVisit(int y, int x, int dir) {
        if (isVisit[y][x]) return false;
        for (int i = 0; i < L - 1; i++) {
            if (checkOver(y + dy[dir] * (i + 1), x + dx[dir] * (i + 1))) return false;
            int now = map[y + dy[dir] * i][x + dx[dir] * i];
            int next = map[y + dy[dir] * (i + 1)][x + dx[dir] * (i + 1)];

            if (now != next) return false;
            if (isVisit[y + dy[dir] * (i + 1)][x + dx[dir] * (i + 1)]) return false;
        }
        return true;
    }

    static boolean checkOver(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

}
