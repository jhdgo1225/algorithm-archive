import java.util.stream.IntStream;

class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String string_zero_to_s = my_string.substring(0, s);
        String overwrite_to_my_string_remainder = my_string.substring(s+overwrite_string.length(), my_string.length());
        return string_zero_to_s + overwrite_string + overwrite_to_my_string_remainder;
    }
}