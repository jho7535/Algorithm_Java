package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 스킬트리 {
    class Solution {
        public int solution(String skill, String[] skill_trees) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < skill.length(); i++) {
                map.put(skill.charAt(i), i);
            }

            int cnt = 0;
            for (String s : skill_trees) {
                boolean flag = true;
                int index = 0;

                for (int i = 0; i < s.length(); i++) {

                    if (map.get(s.charAt(i)) == null) continue;

                    if (map.get(s.charAt(i)) != index++) {
                        flag = false;
                        break;
                    }
                }

                if (flag) cnt++;
            }

            return cnt;
        }
    }
}
