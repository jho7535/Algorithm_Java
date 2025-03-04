package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 튜플 {
    class Solution {
        public List<Integer> solution(String s) {
            String regex = "\\{\\{|\\},\\{|\\}\\}";
            String[] arrays = s.split(regex);
            List<Integer> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();

            Arrays.sort(arrays, (a, b) -> (a.length() - b.length()));

            for (int i = 1; i < arrays.length; i++) {
                String str1 = arrays[i];
                String[] arr = str1.split(",");

                for (String str2 : arr) {
                    int num = Integer.parseInt(str2);

                    // 이미 존재
                    if (set.contains(num)) continue;

                    list.add(num);
                    set.add(num);
                }
            }

            return list;
        }
    }
}
