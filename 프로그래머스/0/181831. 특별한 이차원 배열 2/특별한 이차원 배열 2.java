import java.util.stream.IntStream;

class Solution {
    public int solution(int[][] arr) {
        return IntStream.range(0, arr.length)
            .allMatch(i -> IntStream.range(0, i + 1)
                .allMatch(j -> arr[i][j] == arr[j][i])) ? 1 : 0;
    }
}