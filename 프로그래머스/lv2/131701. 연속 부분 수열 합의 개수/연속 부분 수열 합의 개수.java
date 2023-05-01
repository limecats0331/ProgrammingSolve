import java.util.*;

class Solution {
    Set<Integer> sums = new HashSet<>();
    
    void findSum(int[] elements, int len){
        if (len == elements.length){
            int sum=0;
            for(int i=0;i<elements.length;i++){
                sum += elements[i];
            }
            sums.add(sum);
            return;
        }
        
        for(int i=0;i<elements.length;i++){
            int sum = 0;
            for(int j=0;j<len;j++){
                sum += elements[(i+j)%elements.length];
            }
            sums.add(sum);
            //System.out.println(sum);
        }
    }
    
    public int solution(int[] elements) {
       	for(int i=1;i<=elements.length;i++){
            findSum(elements, i);
        }
        return sums.size();
    }
}