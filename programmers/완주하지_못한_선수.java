package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 완주하지_못한_선수 {
    class Solution {
        public String solution(String[] participant, String[] completion) {
            Map<String, Integer> map = new HashMap<>();
            String answer = "";
            for (String str : completion) map.put(str, map.getOrDefault(str, 0) + 1);

            for (String str : participant) {
                if (!map.containsKey(str)) {
                    answer = str;
                    break;
                }

                map.put(str, map.get(str) - 1);
                if (map.get(str) == 0) map.remove(str);
            }

            return answer;
        }
    }
}
