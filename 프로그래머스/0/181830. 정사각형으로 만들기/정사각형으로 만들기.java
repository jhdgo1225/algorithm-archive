class Solution {
    public int[][] solution(int[][] arr) {
        int rowLen = arr.length;
        int colLen = 0;
        for (int[] row : arr) {
            colLen = Math.max(colLen, row.length);
        }
        int n = Math.max(rowLen, colLen);
        int[][] answer = new int[n][n];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                answer[i][j] = arr[i][j];
            }
        }
        return answer;
    }
}