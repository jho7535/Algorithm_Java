package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 주식가격 {
    class Solution {
        public int[] solution(int[] prices) {
            Stack<Integer> stack = new Stack<>();
            int n = prices.length;
            int[] answer = new int[n];

            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                    int index = stack.pop();
                    answer[index] = i - index;
                }

                stack.push(i);
            }

            while (!stack.isEmpty()) {
                int index = stack.pop();
                answer[index] = n - index - 1;
            }

            return answer;
        }
    }
}
