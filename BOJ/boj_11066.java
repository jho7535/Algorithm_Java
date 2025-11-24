package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_11066 {
    //public class Main {

    static int[] input;
    static int[] preSum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test = 0; test < T; test++) {
            int k = Integer.parseInt(br.readLine());
            input = new int[k + 1];
            preSum = new int[k + 1];
            dp = new int[k + 1][k + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) input[i] = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= k; i++) preSum[i] = preSum[i - 1] + input[i];

            for (int gap = 1; gap < k; gap++) {
                for (int start = 1; start + gap <= k; start++) {
                    int end = start + gap;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + preSum[end] - preSum[start - 1]);
                    }
                }
            }

            sb.append(dp[1][k]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
