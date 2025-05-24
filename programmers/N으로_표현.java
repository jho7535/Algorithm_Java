package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class N으로_표현 {
    class Solution {
        public int solution(int N, int number) {
            List<Set<Integer>> dp = new ArrayList<>();
            for (int i = 0; i <= 8; i++) {
                dp.add(new HashSet<>());
            }

            for (int i = 1; i <= 8; i++) {
                dp.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i)));

                for (int j = 1; j < i; j++) {
                    for (int a : dp.get(i - j)) {
                        for (int b : dp.get(j)) {
                            dp.get(i).add(a + b);
                            dp.get(i).add(a - b);
                            dp.get(i).add(a * b);
                            if (b != 0) dp.get(i).add(a / b);
                        }
                    }
                }
            }

            for (int i = 1; i <= 8; i++) {
                for (int k : dp.get(i)) {
                    if (k == number) return i;
                }
            }

            return -1;
        }
    }
}
