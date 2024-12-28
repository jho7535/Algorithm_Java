package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_11054 {
    //public class Main {
    static int[][] dp;
    static int n;
    static int[] arr;

    public static void main(String[] argv) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[2][n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp[0], 1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i] && dp[0][j] < dp[0][i] + 1) dp[0][j] = dp[0][i] + 1;
            }
        }

        Arrays.fill(dp[1], 1);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i] && dp[1][j] < dp[1][i] + 1) dp[1][j] = dp[1][i] + 1;
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (dp[0][i] + dp[1][i] - 1 > max) max = dp[0][i] + dp[1][i] - 1;
        }
        System.out.println(max);
    }
}
