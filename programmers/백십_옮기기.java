package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 백십_옮기기 {
    class Solution {
        public String[] solution(String[] s) {
            String[] answer = new String[s.length];

            for (int j = 0; j < s.length; j++) {
                StringBuilder sb1 = new StringBuilder();
                int cnt = 0;
                String str = s[j];

                for (int i = 0; i < str.length(); i++) {
                    sb1.append(str.charAt(i));

                    if (sb1.length() >= 3 && sb1.substring(sb1.length() - 3).equals("110")) {
                        sb1.delete(sb1.length() - 3, sb1.length());
                        cnt++;
                    }
                }

                // cnt가 0. list 추가. continue
                if (cnt == 0) {
                    answer[j] = str;
                    continue;
                }

                // 최적의 자리 찾기
                int lastZero = sb1.lastIndexOf("0");
                StringBuilder sb2 = new StringBuilder();
                String insert = "110".repeat(cnt);

                // 0이 있는지 없는지
                if (lastZero != -1) {
                    sb2.append(sb1.substring(0, lastZero + 1));
                    sb2.append(insert);
                    sb2.append(sb1.substring(lastZero + 1, sb1.length()));
                } else {
                    sb2.append(insert);
                    sb2.append(sb1);
                }

                answer[j] = sb2.toString();
            }

            return answer;
        }
    }
}
