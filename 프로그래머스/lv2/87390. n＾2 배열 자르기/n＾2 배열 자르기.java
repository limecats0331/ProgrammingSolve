import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();
        
        long start = (left/n) + 1L;
        long cnt = 0 + (start-1)*n;
        long num = 0;
       	for(long i=start;i<=n;i++){
           for(long j=0;j<i;j++){
               num = i;
               //System.out.println(num);
               if(cnt >= left && cnt <= right){
                   answer.add((int)num);
               }
               cnt++;
           } 
           for(long j=i+1;j<=n;j++){
               num = j;
               //System.out.println(num);
               if(cnt >= left && cnt <= right){
                   answer.add((int)num);
               }
               cnt++;
           }
            if(cnt > right){
                break;
            }
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}