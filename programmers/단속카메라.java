package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 단속카메라 {
    class Solution {
        public int solution(int[][] routes) {
            Arrays.sort(routes, (a, b) -> a[1] - b[1]);
            int answer = 0;
            int end = -50000;
            for (int[] route : routes) {
                if (end >= route[0]) continue;

                answer++;
                end = route[1];
            }

            return answer;
        }
    }
}
