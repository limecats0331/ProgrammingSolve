import java.util.*;

class Solution {
    Map<Character, Integer> order = new HashMap<>();
    
    public int solution(String skill, String[] skill_trees) {
        for(int i=0;i<skill.length();i++){
            order.put(skill.charAt(i),i);
        }
        
        int answer = 0;
        for(String each : skill_trees){
           	if(isOrdered(each)){
				answer++;                
                //System.out.println(each);
            }
        }
            
        return answer;
    }
    
    boolean isOrdered(String skill){
       	List<Integer> input = new ArrayList<>(); 
        for(int i=0;i<skill.length();i++){
            char c = skill.charAt(i);
            if(order.get(c) == null) {
                continue;
            }
           	input.add(order.get(c));
        }
        
        for(int i=0;i<input.size();i++){
        	if( input.get(i) != i) {
                return false;
            }
        }
        return true;
    }
}