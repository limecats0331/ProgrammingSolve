import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //최대 크기가 1000
        int[] time = new int[1001];
        int[] cost = new int[1001];
        //i번째 날부터 끝까지 중에 최대값이 dp[i]에 저장된다.
        int[] dp = new int[1001];
        
        int N = Integer.parseInt(br.readLine().trim());
        int nextDay = 0;

        //입력값 입력
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().trim().split(" ");
            time[i] = Integer.parseInt(input[0]);
            cost[i] = Integer.parseInt(input[1]);
        }

        //마지막 날부터 생각
        for (int i = N - 1; i >= 0; i--) {
            nextDay = i + time[i];
            //만약 다음 날이 퇴사날을 넘는다면 
            if (nextDay > N) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], dp[nextDay] + cost[i]);
            }
        }

        //결과 출력
        System.out.println(dp[0]);
    }

}
