package nestnet_algorithm_2023_2.JeongHanUl.elice_code_challenge;

import java.util.Scanner;
import java.util.Stack;

public class S2_Day_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Stack<Character> start = new Stack<>();
        Stack<Character> end = new Stack<>();

        for (int i = 0; i < input.length(); i++) start.push(input.charAt(i));

        while (!start.isEmpty()) {
            char ch1 = start.pop();

            if (ch1 != '(') {
                end.push(ch1);
                continue;
            }

            StringBuilder sb = new StringBuilder();
            char ch2 = end.pop();
            while (ch2 != ')') {
                sb.append(ch2);
                ch2 = end.pop();
            }
            String str = sb.toString();

            int k = start.pop() - '0';
            for (int j = 0; j < k; j++) {
                for (int p = str.length() - 1; p >= 0; p--) end.push(str.charAt(p));
            }
        }

        System.out.println(end.size());
    }
}
