package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 롤케이크_자르기 {
    class Solution {
        public int solution(int[] topping) {
            Map<Integer, Integer> map = new HashMap<>();
            int[][] cnt = new int[2][topping.length];

            for (int i = 0; i < topping.length; i++) {
                map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);

                cnt[0][i] = map.size();
            }

            map.clear();
            for (int i = topping.length - 1; i > 0; i--) {
                map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);

                cnt[1][i] = map.size();
            }

            int answer = 0;
            for (int i = 0; i < topping.length - 1; i++) {
                if (cnt[0][i] == cnt[1][i + 1]) answer++;
            }

            return answer;
        }
    }
}
