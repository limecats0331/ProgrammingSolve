import java.util.*;

class Solution {
    Set<String> loads = new HashSet<>();
   	int[] location = {0,0}; 
    
    public int solution(String dirs) {
        for(int i=0;i<dirs.length();i++){
            move(dirs.charAt(i));
            //System.out.printf("now = [%d, %d]\n",location[0],location[1]);
        }
       	//System.out.println(loads); 
        return loads.size()/2;
    }
    
   	void move(char dir){
        int y = location[0];
        int x = location[1];
        int dy;
        int dx;
        
        switch (dir){
            case 'U':
                dy = y -1;
                dx = x;
                if(dy < -5) return;
               	loads.add(String.format("%d,%dto%d,%d",y,x,dy,dx));
               	loads.add(String.format("%d,%dto%d,%d",dy,dx,y,x));
                location[0] = dy;
                location[1] = dx;
                break;
            case 'D':
                dy = y +1;
                dx = x;
                if(dy > 5) return;
               	loads.add(String.format("%d,%dto%d,%d",y,x,dy,dx));
               	loads.add(String.format("%d,%dto%d,%d",dy,dx,y,x));
                location[0] = dy;
                location[1] = dx;
                break;
            case 'R':
                dy = y;
                dx = x+1;
                if(dx > 5) return;
               	loads.add(String.format("%d,%dto%d,%d",y,x,dy,dx));
               	loads.add(String.format("%d,%dto%d,%d",dy,dx,y,x));
                location[0] = dy;
                location[1] = dx;
                break;
            case 'L':
                dy = y;
                dx = x-1;
                if(dx < -5) return;
               	loads.add(String.format("%d,%dto%d,%d",y,x,dy,dx));
               	loads.add(String.format("%d,%dto%d,%d",dy,dx,y,x));
                location[0] = dy;
                location[1] = dx;
                break;
        }
    } 
}