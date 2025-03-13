package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;

public class boj_10422 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        long[] dp = new long[5001];
        dp[0] = 1;

        for (int i = 2; i <= 5000; i += 2) {
            for (int j = 2; j <= i; j += 2) {
                dp[i] = (dp[i] + dp[j - 2] * dp[i - j]) % 1_000_000_007;
            }
        }

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            sb.append(dp[k]).append("\n");
        }
        System.out.print(sb);
    }
}
