class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }

    private int dfs(int depth, int total, int[] numbers, int target) {
        if (depth == numbers.length) {
            return total == target ? 1 : 0;
        }
        return dfs(depth + 1, total + numbers[depth], numbers, target) + dfs(depth + 1, total - numbers[depth], numbers, target);
    }
}