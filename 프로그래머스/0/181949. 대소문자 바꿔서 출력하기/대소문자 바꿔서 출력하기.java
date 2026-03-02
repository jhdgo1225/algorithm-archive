import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String result = a.chars()
            .mapToObj(c -> Character.isLowerCase(c) ? Character.toUpperCase((char)c) : Character.toLowerCase((char)c))
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
        System.out.println(result);
    }
}