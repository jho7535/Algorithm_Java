package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14501 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[2][n + 1]; // 0: 시간, 1: 페이
        int[] dp = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n + 1; i++) {
            if (dp[i] < dp[i - 1]) dp[i] = dp[i - 1];

            if (i + arr[0][i] - 1 >= n + 1) continue;

            if (dp[i - 1] + arr[1][i] > dp[i + arr[0][i] - 1]) dp[i + arr[0][i] - 1] = dp[i - 1] + arr[1][i];
        }

        System.out.println(dp[n]);
    }
}
