import java.util.stream.IntStream;
import java.util.Arrays;

class Solution {
    public int solution(int[][] board, int k) {
        return IntStream.range(0, board.length)
            .flatMap(i -> IntStream.range(0, board[i].length)
                .filter(j -> i + j <= k)
                .map(j -> board[i][j]))
            .sum();
    }
}