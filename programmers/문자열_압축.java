package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 문자열_압축 {
    class Solution {
        public int solution(String s) {
            int min = s.length();

            for (int len = 1; len <= s.length() / 2; len++) {
                String a = s.substring(0, len);
                StringBuilder sb = new StringBuilder();
                int cnt = 1;

                int i;
                for (i = len; i < s.length(); i += len) {
                    if (i + len > s.length()) break;

                    String b = s.substring(i, i + len);
                    if (a.equals(b)) {
                        cnt++;
                        continue;
                    }

                    if (cnt > 1) {
                        sb.append(cnt).append(a);
                    } else {
                        sb.append(a);
                    }

                    cnt = 1;
                    a = b;
                }
                if (cnt > 1) sb.append(cnt).append(a);
                else sb.append(a);
                sb.append(s.substring(i));

                min = Math.min(min, sb.toString().length());
            }

            return min;
        }
    }
}
