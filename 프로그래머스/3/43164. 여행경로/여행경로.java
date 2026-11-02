import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            map.putIfAbsent(tickets[i][0], new ArrayList<>());
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        Set<String> keySet = map.keySet();
        for (String key : keySet) map.get(key).sort((a, b) -> b.compareTo(a));
        Deque<String> stack = new ArrayDeque<>();
        LinkedList<String> path = new LinkedList<>();
        stack.push("ICN");

        while (!stack.isEmpty()) {
            String cur = stack.peek();
            List<String> list = map.get(cur);

            if (list != null && !list.isEmpty()) {
                stack.push(list.remove(list.size() - 1));
            } else {
                path.addFirst(stack.pop());
            }
        }

        return path.toArray(new String[0]);
    }
}