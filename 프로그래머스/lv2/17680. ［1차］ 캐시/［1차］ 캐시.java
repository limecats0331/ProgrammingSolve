import java.util.*;

class Solution {
    List<String> cache = new LinkedList<>();
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        for(String city : cities){
            city = city.toLowerCase();
            int index = cache.indexOf(city);
            //System.out.println(cache);
            if(index == -1){ //cache miss
               	answer += 5;
                if(!cache.isEmpty() && cache.size() >= cacheSize){
                    cache.remove(0);
                }
                if(cacheSize == 0){
                    continue;
                }
            	cache.add(city);
            } else { //cache hit
               	answer++; 
                cache.remove(index);
            	cache.add(city);
            }
        }
        
        return answer;
    }
}