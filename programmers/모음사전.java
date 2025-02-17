package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 모음사전 {
    class Solution {
        static char[] ch = new char[]{'A', 'E', 'I', 'O', 'U'};

        public int solution(String word) {
            List<String> list = new ArrayList<>();

            for (char q : ch) {
                list.add(String.valueOf(q));
            }
            for (char q : ch) {
                for (char w : ch) {
                    list.add(q + "" + w);
                }
            }
            for (char q : ch) {
                for (char w : ch) {
                    for (char e : ch) {
                        list.add(q + "" + w + "" + e);
                    }
                }
            }
            for (char q : ch) {
                for (char w : ch) {
                    for (char e : ch) {
                        for (char r : ch) {
                            list.add(q + "" + w + "" + e + "" + r);
                        }
                    }
                }
            }
            for (char q : ch) {
                for (char w : ch) {
                    for (char e : ch) {
                        for (char r : ch) {
                            for (char t : ch) {
                                list.add(q + "" + w + "" + e + "" + r + "" + t);
                            }
                        }
                    }
                }
            }

            Collections.sort(list);
            int cnt = 1;
            for (String str : list) {
                if (str.equals(word)) break;
                cnt++;
            }

            return cnt;
        }
    }
}
