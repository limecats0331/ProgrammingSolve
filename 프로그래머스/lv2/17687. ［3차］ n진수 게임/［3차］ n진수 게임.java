import java.util.*;

class Solution {
    Map<Integer, Character> overTen = new HashMap<>();
    
    Solution(){
        for(int i=0;i<6;i++){
            overTen.put(10+i,(char)('A'+i));
        }
    }
    
    public String solution(int n, int t, int m, int p) {
        String total = "";
        int cnt = 0;
        while(total.length() < t*m){
           	total += toBase(cnt,n);
            cnt++;
        }
        
        String result = "";
        for(int i=0;i<t;i++){
            result += total.charAt(i*m + p-1);
        }
        
        return result;
    }
    
    String toBase(int num, int base){
        String result = "";
		while(num >= base){
            int next = num%base;
            if(next >= 10){
            	result = overTen.get(next) + result;
            } else {
            	result = num%base + result;
            }
            num /= base;
        }
        if(num >= 10){
           	result = overTen.get(num) + result;
        } else {
            result = num + result;
        }
        return result;
    }
}