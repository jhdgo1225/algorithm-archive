import java.util.*;

class Solution {
    private static HashSet<Integer> set;
    public Solution() {
        set = new HashSet<>();
    }
    private boolean checkPrime(int n) {
        if (n < 2) return false;
        for (int i=2; i<=(int)Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;
    }

    private void dfs(int cur, int max, String numbers, boolean[] visited, List<Integer> nums) {
        if (cur == max) {
            int sum = 0;
            for (int num : nums) sum = (sum * 10) + num;
            if (checkPrime(sum)) set.add(sum);
            return ;
        }
        for (int i=0; i<visited.length; i++) {
            if (visited[i]) continue;
            dfs(cur + 1, max, numbers, visited, nums);
            visited[i] = true;
            nums.add(numbers.charAt(i) - '0');
            dfs(cur + 1, max, numbers, visited, nums);
            visited[i] = false;
            nums.remove(nums.size() - 1);
        }
    }
    public int solution(String numbers) {
        int answer = 0;
        List<Integer> nums = new ArrayList<>();
        boolean[] visited = new boolean[numbers.length()];
        Arrays.fill(visited, false);
        dfs(0, numbers.length(), numbers, visited, nums);
        return set.size();
    }
}