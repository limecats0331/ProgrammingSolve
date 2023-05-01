import java.util.*;

class Solution {
    Map<Integer, Integer> cnt = new HashMap<>();
    
    public int solution(int[] priorities, int location) {
        int stop = priorities[location];
        ArrayDeque<int[]> que = new ArrayDeque<>();
        int min = 10;
        int max = 0;
        
        for(int i=0;i<priorities.length;i++){
            int p = priorities[i];
            if(i != location){
        		que.addLast(new int[]{p,0});
            } else {
        		que.addLast(new int[]{p,1});
            }
            if(cnt.get(p) == null){
                cnt.put(p,1);
            } else {
                cnt.put(p,cnt.get(p)+1);
            }
            min = Math.min(min, p);
            max = Math.max(max, p);
        }
        
        int answer = 0;
        while(!que.isEmpty()){
            int[] now = que.pop();
            if(now[0] < max){
                que.addLast(now);
            } else {
                answer ++;
                if(now[1] == 1){
                    return answer;
                }
                if(cnt.get(now[0]) == 1){
                    cnt.remove(now[0]);
                    for(int i=9;i>0;i--){
                        if(cnt.get(i) != null){
                            max = i;
                            break;
                        }
                    }
                } else {
                    cnt.put(now[0],cnt.get(now[0])-1);
                }
            }
        }
        
        return answer;
    }
}