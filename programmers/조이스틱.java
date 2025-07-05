package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 조이스틱 {
    class Solution {
        public int solution(String name) {
            int len = name.length();
            boolean[] bingo = new boolean[len];
            int answer = 0;
            for (int i = 0; i < len; i++) {
                if (name.charAt(i) != 'A') {
                    answer += Math.min((int) name.charAt(i) - 'A', (int) 'A' - name.charAt(i) + 26);
                }
            }

            int minMove = len - 1;
            for (int i = 0; i < len; i++) {
                int next = i + 1;
                while (next < len && name.charAt(next) == 'A') {
                    next++;
                }

                int forwardThenBackward = i * 2 + (len - next);

                int backwardThenForward = (len - next) * 2 + i;

                minMove = Math.min(minMove, forwardThenBackward);
                minMove = Math.min(minMove, backwardThenForward);
            }

            answer += minMove;

            return answer;
        }
    }
}
