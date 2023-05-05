import java.util.*;

class Solution {
    TreeSet<String> dict = new TreeSet<>();
    
    public int solution(String word) {
       	String chars = " AEIOU";
        for(char c1 : chars.toCharArray()){
            for(char c2 : chars.toCharArray()){
        		for(char c3 : chars.toCharArray()){
        			for(char c4 : chars.toCharArray()){
        				for(char c5 : chars.toCharArray()){
        					dict.add(String.format("%c%c%c%c%c",c1,c2,c3,c4,c5).replace(" ",""));	
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        for(String each : dict){
            if(each.equals(word)){
                break;
            }
            answer++;
        }
        return answer;
    }
}