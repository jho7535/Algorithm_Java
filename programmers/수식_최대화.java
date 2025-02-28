package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 수식_최대화 {
    class Solution {
        public long solution(String expression) {
            long max = 0;

            String[] str = expression.split("[+\\-*]");
            ArrayList<Long> originNum = new ArrayList<>();
            for (String n : str) originNum.add(Long.parseLong(n));

            ArrayList<Character> originOperand = new ArrayList<>();
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*')
                    originOperand.add(expression.charAt(i));
            }

            String[] priority = new String[]{"+-*", "+*-", "-+*", "-*+", "*+-", "*-+"};
            for (String p : priority) {
                ArrayList<Long> num = new ArrayList<>(originNum);
                ArrayList<Character> operand = new ArrayList<>(originOperand);

                for (int i = 0; i < 3; i++) {
                    int numIndex = 0, opIndex = 0;

                    while (!operand.isEmpty() && numIndex < num.size() && opIndex < operand.size()) {
                        if (operand.get(opIndex) != p.charAt(i)) {
                            numIndex++;
                            opIndex++;
                            continue;
                        }

                        long a = num.get(numIndex);
                        long b = num.get(numIndex + 1);
                        char op = operand.get(opIndex);

                        num.remove(numIndex);
                        num.remove(numIndex);
                        operand.remove(opIndex);
                        if (op == '+') num.add(numIndex, a + b);
                        if (op == '-') num.add(numIndex, a - b);
                        if (op == '*') num.add(numIndex, a * b);
                    }
                }

                max = Math.max(max, Math.abs(num.get(0)));
            }

            return max;
        }
    }
}
