import java.util.*;

class Solution {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        for(int each : scoville){
            pq.add(each);
        }
        
        int answer = 0;
        while(true){
            int now = pq.poll();
            if(now >= K){
                break;
            }
            if(pq.isEmpty()){
                return -1;
            }
            int next = pq.poll();
           	pq.add(now + next*2);
            answer++;
        }
        
        return answer;
    }
}