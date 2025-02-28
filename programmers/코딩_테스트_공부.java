package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 코딩_테스트_공부 {
    class Solution {
        static int goalAl = 0;
        static int goalCo = 0;
        static int answer = Integer.MAX_VALUE;

        public int solution(int alp, int cop, int[][] problems) {
            for (int[] problem : problems) {
                goalAl = Math.max(goalAl, problem[0]);
                goalCo = Math.max(goalCo, problem[1]);
            }

            int[][] dp = new int[goalAl + 1][goalCo + 1];
            for (int[] arr : dp) Arrays.fill(arr, 10000000);

            alp = Math.min(alp, goalAl);
            cop = Math.min(cop, goalCo);
            dp[alp][cop] = 0;

            for (int i = alp; i <= goalAl; i++) {
                for (int j = cop; j <= goalCo; j++) {
                    if (i + 1 <= goalAl) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                    if (j + 1 <= goalCo) dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                    for (int[] problem : problems) {
                        if (problem[0] > i || problem[1] > j) continue;

                        int nextAl = Math.min((i + problem[2]), goalAl);
                        int nextCo = Math.min((j + problem[3]), goalCo);
                        dp[nextAl][nextCo] = Math.min(dp[nextAl][nextCo], dp[i][j] + problem[4]);
                    }
                }
            }

            return dp[goalAl][goalCo];
        }
    }
}
