import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] result = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<prices.length; i++) {
            if (stack.isEmpty() || prices[stack.peek()] < prices[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                    int prevStock = stack.pop();
                    result[prevStock] = i - prevStock;
                }
                stack.push(i);
            }
        }
        int topStock = -1;
        if (!stack.isEmpty()) topStock = stack.pop();
        while (!stack.isEmpty()) {
            int prevStock = stack.pop();
            result[prevStock] = topStock - prevStock;
        }
        return result;
    }
}