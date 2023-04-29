class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int h1 = arr1.length;
        int w1 = arr1[0].length;
        int h2 = arr2.length;
        int w2 = arr2[0].length;
        
        int[][] answer = new int[h1][w2];
        
        for(int i=0;i<h1;i++){
            for(int j=0;j<w2;j++){
                for(int k =0;k<w1;k++){
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        
        return answer;
    }
}