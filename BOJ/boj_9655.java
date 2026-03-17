package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_9655 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[1001];
        dp[1] = true;
        dp[2] = false;
        dp[3] = true;

        for (int i = 4; i <= n; i++) {
            if (!dp[i - 1] || !dp[i - 3]) dp[i] = true;
        }

        System.out.println(dp[n] ? "SK" : "CY");
    }
}
