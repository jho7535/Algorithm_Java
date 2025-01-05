package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 최댓값과_최솟값 {
    class Solution {
        public String solution(String s) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int cnt = 1;
            StringTokenizer st = new StringTokenizer(s);

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') cnt++;
            }

            for (int i = 0; i < cnt; i++) {
                int num = Integer.parseInt(st.nextToken());

                if (num > max) max = num;
                if (num < min) min = num;
            }


            String answer = Integer.toString(min) + " " + Integer.toString(max);
            return answer;
        }
    }
}