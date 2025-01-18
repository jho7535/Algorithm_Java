package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 연속_펄스_부분_수열의_합 {
    class Solution {
        public long solution(int[] sequence) {
            long[] arr0 = new long[sequence.length];
            long[] arr1 = new long[sequence.length];

            for (int i = 0; i < sequence.length; i++) {
                int d0, d1;
                if (i % 2 == 0) {
                    d0 = 1;
                    d1 = -1;
                }
                else {
                    d1 = 1;
                    d0 = -1;
                }

                arr0[i] = (long) sequence[i] * d0;
                arr1[i] = (long) sequence[i] * d1;
            }

            long[][] dp = new long[2][sequence.length];
            dp[0][0] = arr0[0];
            dp[1][0] = arr1[0];
            for (int i = 1; i < sequence.length; i++) {
                dp[0][i] = Math.max(dp[0][i - 1] + arr0[i], arr0[i]);
                dp[1][i] = Math.max(dp[1][i - 1] + arr1[i], arr1[i]);
            }

            long max0 = Long.MIN_VALUE;
            long max1 = Long.MIN_VALUE;
            for (int i = 0; i < sequence.length; i++) {
                max0 = Math.max(max0, dp[0][i]);
                max1 = Math.max(max1, dp[1][i]);
            }

            return Math.max(max0, max1);
        }
    }
}
