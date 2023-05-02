import java.util.*;

class Solution {
    Set<String> numbers = new HashSet<>();
    
    public boolean solution(String[] phone_book) {
   		for(String phone : phone_book){
            numbers.add(phone);
        }     
        
        for(int i=0;i<phone_book.length;i++){
            String phone = phone_book[i];
            for(int j=0;j<phone.length();j++){
                if(numbers.contains(phone.substring(0,j))){
                    return false;
                }
            }
        }
        
        return true;
    }
}