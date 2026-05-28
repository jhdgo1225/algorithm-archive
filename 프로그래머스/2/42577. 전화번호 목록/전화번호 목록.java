import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public boolean solution(String[] phonebook) {
        Arrays.sort(phonebook, Collections.reverseOrder());
        boolean answer = true;
        HashSet<String> set = new HashSet<>();
        for (String phone : phonebook) {
            if (set.contains(phone)) {
                answer = false;
                break;
            }
            for (int i=0; i<phone.length(); i++) {
                String slicedStr= phone.substring(0, i+1);
                set.add(slicedStr);
            }
        }
        return answer;
    }
}