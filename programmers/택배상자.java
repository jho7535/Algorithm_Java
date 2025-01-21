package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 택배상자 {
    class Solution {
        public int solution(int[] order) {
            int[] newOrder = new int[order.length];
            for (int i = 0 ; i < order.length; i++) {
                newOrder[order[i] - 1] = i;
            }
            Stack<Integer> stack = new Stack<>();

            int cnt = 0;
            int index = 0;
            while (cnt < order.length) {
                if (!stack.isEmpty() && stack.peek() == cnt) {
                    stack.pop();
                    cnt++;
                    continue;
                }

                while (index < order.length) {
                    if (newOrder[index] != cnt) {
                        stack.push(newOrder[index++]);
                    } else {
                        index++;
                        cnt++;
                        break;
                    }
                }

                if ((!stack.isEmpty() && stack.peek() != cnt) && index >= order.length) break;
            }

            return cnt;
        }
    }
}
