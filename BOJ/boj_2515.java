package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2515 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[][] paintings = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            paintings[i][0] = Integer.parseInt(st.nextToken());
            paintings[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[20_000_001];
        for (int[] painting : paintings) {
            int high = painting[0];
            int value = painting[1];

            dp[high] = Math.max(dp[high], value);
        }
        for (int i = 1; i < s; i++) dp[i] = 0;

        for (int i = s + 1; i <= 20_000_000; i++) {
            dp[i] = Math.max(dp[i] + dp[i - s], dp[i - 1]);
        }

        System.out.println(dp[20_000_000]);
    }
}
