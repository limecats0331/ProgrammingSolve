import java.util.*;

class Solution {
    String spinString(String input, int num){
		char[] newString = new char[input.length()];
        
        for(int i=0;i<input.length();++i){
            int location = (i+num) % input.length();
            newString[i] = input.charAt(location);
        }
        
        return new String(newString);
    }
    
    boolean defBracket(String input){
        List<Character> answer = new ArrayList<>();
        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            if( c == '(' || c == '[' || c == '{'){
                answer.add(c);
            } else {
                int last = answer.size()-1;
                if( c == ')' && answer.size() > 0 && answer.get(last) == '(' ){
                    answer.remove(last);
                } else if(c == '}' && answer.size() > 0 && answer.get(last) == '{') {
                    answer.remove(last);
                } else if(c == ']' && answer.size() > 0 && answer.get(last) == '[') {
                    answer.remove(last);
                } else {
                    return false;
                }
            }
        }
        return answer.size() == 0;
    }
    
    public int solution(String s) {
        int answer = 0;
        
        for(int i =0;i<s.length();i++){
        	String spined = spinString(s,i);
            if(defBracket(spined)){
                answer++;
            }
        }
        
        return answer;
    }
}