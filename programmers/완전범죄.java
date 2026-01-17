package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;


public class 완전범죄 {
    class Solution {
        public int solution(int[][] info, int n, int m) {
            int[] dp = new int[m];
            Arrays.fill(dp, 100_000_000);
            dp[0] = 0;

            for (int[] in : info) {
                int a = in[0];
                int b = in[1];

                for (int i = m - 1; i >= 0; i--) {
                    if (dp[i] == 100_000_000) continue;

                    if (i + b < m) {
                        dp[i + b] = Math.min(dp[i + b], dp[i]);
                    }

                    dp[i] = dp[i] + a;
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                min = Math.min(min, dp[i]);
            }

            return min >= n ? -1 : min;
        }
    }
}
