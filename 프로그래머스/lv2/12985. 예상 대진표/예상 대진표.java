import java.util.*;

class Solution{
    int changeToNext(int num){
        return num%2==0 ? num/2 : (num+1)/2;
    }
    
    public int solution(int n, int a, int b){
        int answer = 1;
        
       	while( Math.abs(a-b) != 1 || (a/2 == b/2) ){
            a = changeToNext(a);
            b = changeToNext(b);
            answer++;
            n /= 2;
        }
        
        return answer;
    }
}