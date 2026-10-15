import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        List<String> stack = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') stack.add("(");
            else {
                if (stack.isEmpty()) answer = false;
                else stack.remove(stack.size() - 1);
            }
        }
        if (!stack.isEmpty())
            answer = false;
        return answer;
    }
}