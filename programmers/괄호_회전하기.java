package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 괄호_회전하기 {
    class Solution {
        public int solution(String s) {
            int cnt = 0;

            for (int t = 0; t < s.length(); t++) {
                Stack<Character> stack = new Stack<>();
                int index = t;
                boolean flag = true;

                do {
                    char ch;
                    if (s.charAt(index) == '[' || s.charAt(index) == '{' || s.charAt(index) == '(') {
                        stack.push(s.charAt(index));
                        index = (index + 1) % s.length();
                        continue;
                    }

                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }

                    ch = stack.pop();

                    if ((s.charAt(index) == ']' && ch != '[') ||
                            (s.charAt(index) == '}' && ch != '{') ||
                            (s.charAt(index) == ')' && ch != '(')) {
                        flag = false;
                        break;
                    }

                    index = (index + 1) % s.length();
                } while (index != t);

                if (flag && stack.isEmpty()) cnt++;
            }

            return cnt;
        }
    }
}
