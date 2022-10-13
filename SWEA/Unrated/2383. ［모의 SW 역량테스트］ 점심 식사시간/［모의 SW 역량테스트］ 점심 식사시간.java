import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static List<Person> persons;
    static List<Stair> stair;
    static List<Person> stack1;
    static List<Person> stack2;
    static int result;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            stair = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int[] line = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < N; j++) {
                    if (line[j] == 1) {
                        persons.add(new Person(i, j));
                    } else if (line[j] > 1) {
                        stair.add(new Stair(i, j, line[j]));
                    }
                }
            }

            result = Integer.MAX_VALUE;
            stack1 = new ArrayList<>();
            stack2 = new ArrayList<>();
            combi(0);
            System.out.printf("#%d %d\n", t, result);
        }
    }

    static int findEndTime(List<Person> stack, int stairTime) {
        if (stack.size() == 0) {
            return 0;
        }
        stack.sort((a, b) -> a.distance - b.distance);
        int[] time = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
//            System.out.println(stack.get(i));
            int dist = stack.get(i).distance;
            if (i < 3) {
                time[i] = dist + stairTime + 1;
            } else {
                if (dist < time[i - 3]) {
                    time[i] = time[i - 3] + stairTime;
                } else {
                    time[i] = dist + stairTime + 1;
                }
            }
        }
        return time[stack.size() - 1];
    }

    static void combi(int depth) {
        if (depth == persons.size()) {
//            System.out.println("=========stack1=============");
            int time1 = findEndTime(stack1, stair.get(0).time);
//            System.out.println("=========stack2=============");
            int time2 = findEndTime(stack2, stair.get(1).time);
            result = Math.min(result, Math.max(time1, time2));
//            System.out.println("time1 = " + time1 + " time2 = "+time2);
//            System.out.println("result = " + result);
            return;
        }

        persons.get(depth).getDistance(stair.get(0).y, stair.get(0).x);
        stack1.add(persons.get(depth));
        combi(depth + 1);
        stack1.remove(persons.get(depth));

        persons.get(depth).getDistance(stair.get(1).y, stair.get(1).x);
        stack2.add(persons.get(depth));
        combi(depth + 1);
        stack2.remove(persons.get(depth));
    }


    static class Stair {
        int y;
        int x;
        int time;

        public Stair(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    static class Person {
        int y;
        int x;
        int distance;

        public Person(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public void getDistance(int y, int x) {
            this.distance = Math.abs(this.y - y) + Math.abs(this.x - x);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "y=" + y +
                    ", x=" + x +
                    ", distance=" + distance +
                    '}';
        }
    }
}
