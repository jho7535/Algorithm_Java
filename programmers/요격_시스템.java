package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 요격_시스템 {
    class Solution {
        public int solution(int[][] targets) {
            Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

            int answer = 0;
            int prev = -1;

            for (int[] target : targets) {
                if (prev < target[0]) {
                    prev = target[1] - 1;
                    answer++;
                }
            }

            return answer;
        }
    }
}
