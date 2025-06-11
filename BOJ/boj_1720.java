package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1720 {
    //public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        // dp로 모든 경우 구하기
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] * 2;
        }

        // 중복이 되는 경우 제거
        if (n % 2 != 0) System.out.println((dp[n] + dp[n / 2]) / 2);
        else System.out.println((dp[n] + dp[n / 2] + dp[n / 2 - 1] * 2) / 2);
    }
}
