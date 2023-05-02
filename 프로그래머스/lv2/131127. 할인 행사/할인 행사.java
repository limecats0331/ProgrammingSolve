import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
    	Map<String, Integer> cnt = new HashMap<>();
        for(int i=0;i<want.length;i++){
           	cnt.put(want[i], number[i]); 
        }
        
        int answer = 0;
        for(int i=0;i<=discount.length-10;i++){
            Map<String, Integer> newCnt = new HashMap<>(cnt);
           	if(def(newCnt, discount, i)){
                System.out.println(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    boolean def(Map<String, Integer> cnt, String[] discount, int start){
        //System.out.printf("cnt = %s\n",cnt);
        for(int i=start;i<start+10;i++){
            String each = discount[i];
            if(cnt.get(each) != null){
                if(cnt.get(each) == 1){
                    cnt.remove(each);
                } else {
                    cnt.put(each, cnt.get(each) -1 );
                }
            }
        }
        
        //System.out.printf("cnt = %s, start = %d\n",cnt, start);
        return cnt.size() == 0;
    }
}