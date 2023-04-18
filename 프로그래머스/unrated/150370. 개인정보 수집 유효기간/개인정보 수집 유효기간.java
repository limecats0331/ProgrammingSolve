import java.util.*;

class Solution {
    Map<String, Integer> termMap = new HashMap<>(); 
    
    Boolean isEnd(String date, String type, String today){
       	String[] splitedDate = date.split("[.]");
        int dateYear = Integer.parseInt(splitedDate[0]);
        int dateMonth = Integer.parseInt(splitedDate[1]);
        int dateDay = Integer.parseInt(splitedDate[2]);
        
        int addMonth = termMap.get(type) % 12;
        int addYear = termMap.get(type) / 12;
        
        dateMonth += addMonth;
        if(dateMonth > 12){
            dateMonth %= 12;
            addYear++;
        }
        
        dateYear += addYear;
        
        if(dateDay == 1){
            dateDay = 28;
            dateMonth--;
            if(dateMonth == 0){
                dateYear --;
                dateMonth = 12;
            }
        } else {
            dateDay--;
        }
        
       	String[] splitedToday = today.split("[.]");
        int todayYear = Integer.parseInt(splitedToday[0]);
        int todayMonth = Integer.parseInt(splitedToday[1]);
        int todayDay = Integer.parseInt(splitedToday[2]);
        
        String todayComp = String.join(".",splitedToday);
        
        String stringDateMonth = String.valueOf(dateMonth);
        if(stringDateMonth.length() == 1){
            stringDateMonth = String.join("",List.of("0", stringDateMonth));
        }
        String stringDateDay = String.valueOf(dateDay);
        if(stringDateDay.length() == 1){
            stringDateDay = String.join("",List.of("0", stringDateDay));
        }
        String dateComp = String.join(".", List.of(String.valueOf(dateYear),stringDateMonth,stringDateDay));
        
        //System.out.printf("todayComp : %s, dateComp : %s, compare : %d\n",todayComp,dateComp,todayComp.compareTo(dateComp));
       
        return todayComp.compareTo(dateComp) > 0;
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        for(String term : terms){
            String[] splited = term.split(" ");
           	termMap.put(splited[0],Integer.parseInt(splited[1]));
        }
        
        for(int i=0;i<privacies.length;i++){
            String[] splited = privacies[i].split(" ");
            if(isEnd(splited[0],splited[1],today)){
                answer.add(i+1);
            }
        }
        
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}