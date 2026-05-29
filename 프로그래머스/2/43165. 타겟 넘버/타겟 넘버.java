class Solution {
    private static int _curTotal;
    private static int _target;
    private static int _count;
    private static int _maxDepth;
    public void dfs(int depth, int[] numbers) {
        if (depth == _maxDepth) {
            if (_curTotal == _target) _count++;
            return ;
        }
        _curTotal += numbers[depth];
        dfs(depth + 1, numbers);
        _curTotal -= numbers[depth];
        _curTotal += numbers[depth] * -1;
        dfs(depth + 1, numbers);
        _curTotal -= numbers[depth] * -1;
    }
    
    public int solution(int[] numbers, int target) {
        _target = target;
        _maxDepth = numbers.length;
        dfs(0, numbers);
        return _count;
    }
}