import java.util.*;

class Solution {
    Map<String, Integer> dict = new HashMap<>();
    
    public Solution(){
        String start = "A";
        for(int i = 0;i<26;i++){
            char input = (char) ('A' + i);
            dict.put(String.valueOf(input),i+1);
        }
    }
    
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        
        for(int i=0;i<msg.length();i++){
            //System.out.printf("start = %d, dict = %s\n",i,dict);
            int out = 0;
           	for(int j=1;j<=msg.length()-i;j++){
                String sub = msg.substring(i,i+j);
               	boolean def = dict.containsKey(sub);
                if(def){
                    out = dict.get(sub);
                    if(i+j == msg.length()){
                        i += j;
                        break;
                    }
                } else {
                    //System.out.println(sub);
                    dict.put(sub,dict.size()+1);
                    i += j-2;
                    break;
                }
            }
           	answer.add(out); 
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}