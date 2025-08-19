package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2133 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 1];
        dp[0] = 1L;
        if (n > 1) dp[2] = 3L;
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * dp[2];

            for (int j = 4; j <= i; j+= 2) {
                dp[i] += dp[i - j] * 2;
            }
        }

        System.out.println(dp[n]);
    }
}
