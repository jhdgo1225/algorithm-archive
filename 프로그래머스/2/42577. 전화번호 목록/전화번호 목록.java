import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public boolean solution(String[] phonebook) {
        Arrays.sort(phonebook);
        for (int i=0; i<phonebook.length - 1; i++) {
            if (phonebook[i].length() < phonebook[i+1].length() && phonebook[i].compareTo(phonebook[i+1].substring(0, phonebook[i].length())) == 0)
                return false;
        }
        return true;
    }
}