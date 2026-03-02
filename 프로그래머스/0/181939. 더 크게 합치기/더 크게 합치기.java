
class Solution {
    private int concatenate_two_number(int num1, int num2) {
        int digits = 0;
        int tmp_num2 = num2;
        while (tmp_num2 != 0) {
            tmp_num2 /= 10;
            digits += 1;
        }
        return num1 * (int)Math.pow(10, digits) + num2;
    }

    public int solution(int a, int b) {
        int a_to_b = concatenate_two_number(a, b);
        int b_to_a = concatenate_two_number(b, a);
        int answer = (a_to_b > b_to_a) ? a_to_b : b_to_a;
        return answer;
    }
}