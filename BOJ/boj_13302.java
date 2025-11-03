package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
 * 쿠폰 3장 -> 하루 이용권
 * n일 중 갈 수 없는 날 m일. 모든 날 감.
 *
 * 입 |   n, m : 1-100, 0-n
 *       m개
 * 출 |   모든 리조트 이용 최솟값
 *
 * dp[][]
 * 쉬는 날 : 안 사도 됨
 * 안 쉬는 날 : 꼭 사야 됨
 * */

public class boj_13302 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] date = new boolean[n + 1];
        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) date[Integer.parseInt(st.nextToken())] = true;
        }

        int[][] dp = new int[n + 1][201];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], 100_000_000);
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            if (date[i]) {
                for (int j = 0; j <= 200; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                }
            }

            dp[i][0] = Math.min(dp[i][0], dp[i - 1][0] + 10000);
            dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + 10000);
            dp[i][2] = Math.min(dp[i][2], dp[i - 1][2] + 10000);

            for (int j = i; j <= n && j < i + 3; j++) {
                dp[j][1] = Math.min(dp[j][1], dp[i - 1][0] + 25000);
                dp[j][2] = Math.min(dp[j][2], dp[i - 1][1] + 25000);
            }

            for (int j = i; j <= n && j < i + 5; j++) {
                dp[j][2] = Math.min(dp[j][2], dp[i - 1][0] + 37000);
            }

            for (int j = 3; j <= 200; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 10000);

                for (int k = i; k <= n && k < i + 3; k++) {
                    dp[k][j] = Math.min(dp[k][j], dp[i - 1][j - 1] + 25000);
                }

                for (int k = i; k <= n && k < i + 5; k++) {
                    dp[k][j] = Math.min(dp[k][j], dp[i - 1][j - 2] + 37000);
                }
            }

            for (int j = 3; j <= 200; j++) {
                dp[i][j - 3] = Math.min(dp[i][j - 3], dp[i - 1][j]);
            }
        }

        int min = 100_000_000;
        for (int i = 0; i <= 200; i++) min = Math.min(min, dp[n][i]);

        System.out.println(min);
    }
}
