package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 연속_부분_수열_합의_개수 {
    class Solution {
        public int solution(int[] elements) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int len = 1; len <= elements.length; len++) {
                for (int i = 0; i < elements.length; i++) {
                    int cnt = 0, sum = 0;
                    int index = i;

                    while (cnt < len) {
                        index %= elements.length;
                        sum += elements[index];
                        cnt++;
                        index++;
                    }

                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }

            return map.size();
        }
    }
}
