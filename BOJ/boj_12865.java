package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_12865 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 해당 무게에서 가능한 최대 가치
        int[] dp = new int[k + 1];
        int[][] input = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            input[i][0] = w;
            input[i][1] = v;
        }

        for (int i = 0; i < n; i++) {
            int w = input[i][0];
            int v = input[i][1];

            for (int j = k; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        System.out.println(dp[k]);
    }
}
