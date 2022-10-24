import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static char[][] map;
	static int H;
	static int W;
	static int[] start;
	static int[] end;
	
	//상우하좌
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	//dir, can conn
	static Map<Integer,Set<Character>> pass = new HashMap<Integer, Set<Character>>(){{
		put(0,new HashSet<Character>());
		put(1,new HashSet<Character>());
		put(2,new HashSet<Character>());
		put(3,new HashSet<Character>());
	}};

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("terner.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//방향 별로 들어 갈 수 잇는 길 목록
		String[] dirPass = {"|+14","-+34","|+23","-+12"};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				pass.get(i).add(dirPass[i].charAt(j));
			}
		}
		
		//케이스 마다 반복
			String[] input = br.readLine().trim().split(" ");
			H = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			
			map = new char[H][W];
			
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().trim().toCharArray();
			}
			
			//탐색을 위한 변수들
			int con = 0;
			int[] location = null;
			List<Integer> isPass = null;
			
			
			find:
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					//모든 맵을 돌면서 빈칸을 찾음
					if(map[i][j] == '.') {
						con = 0;
						isPass = new ArrayList<>();
						
						//상우하좌 순으로 돌면서 들어갈 수 있는 길이 몇개인지 확인함
						for (int k = 0; k < 4; k++) {
							int ny = i + dy[k];
							int nx = j + dx[k];
							
							if(ny<0||ny>=H||nx<0||nx>=W) continue;
							
							//인접한 부분이 도로면
							if(map[ny][nx] != '.') {
								//인접한 부분이 들어갈 수 있는 도로면
								if(pass.get(k).contains(map[ny][nx])) {
									con ++;
									isPass.add(k);
								}
							}
						}
						
						//만약 연결이 2개 이상 되어 있으면 위치를 저장하고 탐색을 종료
						if(con > 1) {
							location = new int[] {i+1,j+1};
							break find;
						}
					}
					
				}
			}
			
			System.out.printf("%d %d %c\n",location[0],location[1],findLoad(isPass));
	}
	
	//연결가능한 위치들을 입력받으면 그에 맞는 도로를 찾아주는 함수
	static char findLoad(List<Integer> pass) {
		Map<Character,List<Integer>> passMap = new HashMap<Character, List<Integer>>() {{
			put('|',new ArrayList<Integer>());
			put('-',new ArrayList<Integer>());
			put('+',new ArrayList<Integer>());
			put('1',new ArrayList<Integer>());
			put('2',new ArrayList<Integer>());
			put('3',new ArrayList<Integer>());
			put('4',new ArrayList<Integer>());
		}};

		//|
		passMap.get('|').add(0);
		passMap.get('|').add(2);
		//-
		passMap.get('-').add(1);
		passMap.get('-').add(3);
		//+
		passMap.get('+').add(0);
		passMap.get('+').add(1);
		passMap.get('+').add(2);
		passMap.get('+').add(3);
		//1
		passMap.get('1').add(1);
		passMap.get('1').add(2);
		//2
		passMap.get('2').add(0);
		passMap.get('2').add(1);
		//3
		passMap.get('3').add(0);
		passMap.get('3').add(3);
		//4
		passMap.get('4').add(2);
		passMap.get('4').add(3);
		
		//탐색 순서가 정해져있기 때문에 equals로 비교해도 찾을 수 있다.
		for (char load : passMap.keySet()) {
			if(passMap.get(load).equals(pass)) {
				return load;
			}
		}
		
		return '_';
	}
}
