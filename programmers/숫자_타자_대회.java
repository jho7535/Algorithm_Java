package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 숫자_타자_대회 {
    class Solution {
        public int solution(String numbers) {
            int[][] arr = new int[10][];
            arr[0] = new int[]{1, 7, 6, 7, 5, 4, 5, 3, 2, 3};
            arr[1] = new int[]{7, 1, 2, 4, 2, 3, 5, 4, 5, 6};
            arr[2] = new int[]{6, 2, 1, 2, 3, 2, 3, 5, 4, 5};
            arr[3] = new int[]{7, 4, 2, 1, 5, 3, 2, 6, 5, 4};
            arr[4] = new int[]{5, 2, 3, 5, 1, 2, 4, 2, 3, 5};
            arr[5] = new int[]{4, 3, 2, 3, 2, 1, 2, 3, 2, 3};
            arr[6] = new int[]{5, 5, 3, 2, 4, 2, 1, 5, 3, 2};
            arr[7] = new int[]{3, 4, 5, 6, 2, 3, 5, 1, 2, 4};
            arr[8] = new int[]{2, 5, 4, 5, 3, 2, 3, 2, 1, 2};
            arr[9] = new int[]{3, 6, 5, 4, 5, 3, 2, 4, 2, 1};

            int[][][] dp = new int[numbers.length() + 1][10][10];
            for (int i = 0; i <= numbers.length(); i++) {
                for (int j = 0; j < 10; j++) {
                    Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                }
            }

            dp[0][4][6] = 0;
            for (int i = 1; i <= numbers.length(); i++) {
                int num = numbers.charAt(i - 1) - '0';

                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        if (dp[i - 1][j][k] == Integer.MAX_VALUE || j == k) continue;

                        dp[i][num][k] = Math.min(dp[i][num][k], dp[i - 1][j][k] + arr[j][num]);
                        dp[i][j][num] = Math.min(dp[i][j][num], dp[i - 1][j][k] + arr[k][num]);
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++){
                    answer = Math.min(answer, dp[numbers.length()][j][k]);
                }
            }

            return answer;

        }
    }
}
