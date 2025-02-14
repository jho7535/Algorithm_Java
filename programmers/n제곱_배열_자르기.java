package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class n제곱_배열_자르기 {
    class Solution {
        public List<Long> solution(int n, long left, long right) {
            List<Long> answer = new ArrayList<>();

            long start = left / n * n;
            for (long i = start; i <= right;) {
                long num = i / n + 1;
                long cnt = num;

                long index = i;
                for (long j = index; j < index + n && i <= right; j++) {
                    if (cnt > 0) {
                        answer.add(num);
                        cnt--;
                    } else {
                        answer.add(++num);
                    }

                    if (i < left) answer.remove(0);
                    i++;
                }
            }

            return answer;
        }
    }
}
