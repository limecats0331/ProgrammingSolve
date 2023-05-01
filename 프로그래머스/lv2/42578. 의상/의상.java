import java.util.*;

class Solution {
    Map<String, Integer> cloth = new HashMap<>();
    
    public int solution(String[][] clothes) {
        for(String[] c : clothes){
           	if(cloth.get(c[1]) == null){
                cloth.put(c[1],1);
            } else {
                cloth.put(c[1],cloth.get(c[1])+1);
            }
        }
        
        int answer = 1;
        for(int value : cloth.values()){
            answer *= value + 1;
        }
        
        return answer - 1;
    }
}