import java.util.*;

class Solution {
    String toPrime(int n, int k){
        String result = "";
        while(n >= k){
           	result = String.valueOf(n%k) + result; 
            n /= k;
        }
        result = String.valueOf(n) + result; 
        return result.toString();
    }
    
    boolean isPrime(long n){
        if(n == 1) return false;
        int end = (int)Math.floor(Math.sqrt(n));
        for(int i=2;i<=end;i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
    
    public int solution(int n, int k) {
        String str = toPrime(n,k);
        String[] splited = str.split("0");
        
        int answer = 0;
        for(String s : splited){
            if(s.trim().isEmpty()) continue;
            //int def = Integer.parseInt(s);
            long def = Long.parseLong(s);
           	if(isPrime(def)){
                answer++;
            } 
        }
        
        return answer;
    }
}