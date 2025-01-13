package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 뒤에_있는_큰_수_찾기 {
    class Solution {
        public int[] solution(int[] numbers) {
            int[] answer = new int[numbers.length];
            Stack<Integer> stack = new Stack<>();
            answer[numbers.length - 1] = -1;
            stack.push(numbers[numbers.length - 1]);

            for (int i = numbers.length - 2; i >= 0; i--) {
                while (!stack.isEmpty()) {
                    if (numbers[i] >= stack.peek()) {
                        stack.pop();
                    } else {
                        answer[i] = stack.peek();
                        stack.push(numbers[i]);
                        break;
                    }
                }

                if (stack.isEmpty()) {
                    answer[i] = -1;
                    stack.push(numbers[i]);
                }
            }

            return answer;
        }
    }
}
