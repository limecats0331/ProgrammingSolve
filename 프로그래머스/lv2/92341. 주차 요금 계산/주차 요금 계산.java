import java.util.*;

class Solution {
    Map<Integer, String> carIn = new TreeMap<>();
    Map<Integer, Integer> carTime = new TreeMap<>();
        
    public int[] solution(int[] fees, String[] records) {
       	for(String each : records){
            String[] record = each.split(" ");
            String time = record[0];
            int carNum = Integer.parseInt(record[1]);
            String command = record[2];
            
            if(carTime.get(carNum) == null){
                carTime.put(carNum,0);
            }
            
            if(command.equals("IN")){ //IN 일때
               	carIn.put(carNum, time);
            } else { // OUT 일 때
               	int addTime =  calcTime(carIn.get(carNum), time);
                carIn.remove(carNum);
                
                carTime.put(carNum, carTime.get(carNum) + addTime);
            }
        } 
        
        for(int carNum : carIn.keySet()){
            int addTime =  calcTime(carIn.get(carNum), "23:59");
            carTime.put(carNum, carTime.get(carNum) + addTime);
        }
        
       	List<Integer> answer = new ArrayList<>(); 
        //System.out.println(carTime);
       	for(int time : carTime.values()) {
            //System.out.println(time);
            answer.add(calcFee(time, fees));
        }
        
        return answer.stream()
            .mapToInt(Integer::valueOf)
            .toArray();
    }
    
    int calcFee(int time, int[] fees){
        time -= fees[0];
        if(time <= 0){
            return fees[1];
        }
        
        return fees[1] + (int)Math.ceil((double)time / (double)fees[2]) * fees[3];
    }
    
    int calcTime(String startTime, String endTime){
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");
        
        return Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]) 
            - Integer.parseInt(start[0])*60 - Integer.parseInt(start[1]);
    }
}